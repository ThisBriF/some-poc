package ph.matt.activitylogger.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ph.matt.activitylogger.model.ProductEvent;
import ph.matt.activitylogger.repository.ProductEventRepository;

@RestController
@Log4j2
public class ProductEventController {

    @Value("${kafka.topic}")
    private String productTopic;

    @Autowired
    ProductEventRepository eventRepository;

    @KafkaListener(topics = "product")
    public void listener(@Payload String message, ConsumerRecord<String, String> cr) {
        log.info("Topic [{}] Received message: [{}]", productTopic, message);
        ProductEvent event = new ProductEvent(message);
        eventRepository.save(event);
    }

}
