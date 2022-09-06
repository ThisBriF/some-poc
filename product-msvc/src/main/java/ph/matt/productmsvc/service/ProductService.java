package ph.matt.productmsvc.service;

import java.util.List;

import ph.matt.productmsvc.controller.dto.ProductDto;
import ph.matt.productmsvc.controller.dto.ProductNoIdDto;
import ph.matt.productmsvc.entity.Product;

public interface ProductService {

    List<Product> getAllProducts();

    ProductDto addProduct(ProductNoIdDto productToPost);

    ProductDto addOrReplaceProduct(ProductDto productToPut);

    ProductNoIdDto getProduct(Integer productId);

}
