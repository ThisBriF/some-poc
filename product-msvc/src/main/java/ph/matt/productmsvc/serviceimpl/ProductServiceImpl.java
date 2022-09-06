package ph.matt.productmsvc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ph.matt.productmsvc.controller.dto.ProductDto;
import ph.matt.productmsvc.controller.dto.ProductNoIdDto;
import ph.matt.productmsvc.entity.Product;
import ph.matt.productmsvc.exception.ResourceNotFoundException;
import ph.matt.productmsvc.repository.ProductRepository;
import ph.matt.productmsvc.service.ProductService;
import ph.matt.productmsvc.util.ProductMapper;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Value("${kafka.topic}")
    private String PRODUCT_TOPIC;
    private String PRODUCT_SAVE_MESSAGE = "New product with ID %d saved.";
    private String PRODUCT_REPLACED_MESSAGE = "Product with ID %d replaced.";
    private String PRODUCT_LOOKUP_MESSAGE = "Product with ID %d retrieved.";

    private final ProductRepository repo;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public List<Product> getAllProducts() {
        log.info(".findAll() called.");

        String message = "All products retrieved from DB.";
        sendMessage(message);

        return repo.findAll();
    }

    @Override
    public ProductDto addProduct(ProductNoIdDto noIdDto) {
        Product productToPost = ProductMapper.dtoToEntity(noIdDto);

        log.info(".saveAndFlush() called.");
        Product postedProduct = repo.saveAndFlush(productToPost);
        log.info(".saveAndFlush() finished. Product with ID {} saved.", postedProduct.getId());

        String message = String.format(PRODUCT_SAVE_MESSAGE, postedProduct.getId());
        sendMessage(message);

        return ProductMapper.entityToProductDto(productToPost);
    }

    @Override
    public ProductDto addOrReplaceProduct(ProductDto dto) {
        Product productToPut = ProductMapper.dtoToEntity(dto);

        log.info(".saveAndFlush() called.");
        Product productPlaced = repo.saveAndFlush(productToPut);

        if (dto.getId() == null) { // TODO might have a better way of checking this
            log.info(".saveAndFlush() finished. Product with ID {} saved.", productPlaced.getId());
            String message = String.format(PRODUCT_SAVE_MESSAGE, productPlaced.getId());
            sendMessage(message);
        } else {
            log.info(".saveAndFlush() finished. Product with ID {} replaced.", productPlaced.getId());
            String message = String.format(PRODUCT_REPLACED_MESSAGE, productPlaced.getId());
            sendMessage(message);
        }

        return ProductMapper.entityToProductDto(productToPut);
    }

    @Override
    public ProductNoIdDto getProduct(Integer productId) {
        log.info(".findById() called.");
        Optional<Product> found = repo.findById(productId);

        if (!found.isPresent()) {
            log.warn("Product retrieval failure using product ID: {}", productId);
            throw new ResourceNotFoundException("Product does not exist.");
        } else {
            log.info(".findById() finished. Found product with ID: {}", productId);
            String message = String.format(PRODUCT_LOOKUP_MESSAGE, found.get().getId());
            sendMessage(message);
        }

        return ProductMapper.entityToProductNoIdDto(found.get());
    }

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(PRODUCT_TOPIC, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Sent message=[{}] with offset={}}", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.warn("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }

        });

    }

}