package ph.matt.productmsvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ph.matt.productmsvc.controller.dto.ProductDto;
import ph.matt.productmsvc.controller.dto.ProductNoIdDto;
import ph.matt.productmsvc.entity.Product;
import ph.matt.productmsvc.service.ProductService;

@RestController
@Log4j2
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Received GET request to retrieve all products");
        List<Product> products = service.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> postProduct(@RequestBody ProductNoIdDto product) {
        log.info("Received POST request to add product: {}", product);
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDto> putProduct(@RequestBody ProductDto product) {
        log.info("Received PUT request to add (or replace) product: {}", product);
        return new ResponseEntity<>(service.addOrReplaceProduct(product), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductNoIdDto> getProduct(@PathVariable Integer productId) {
        log.info("Received GET request to retrieve product with ID {}", productId);
        return new ResponseEntity<>(service.getProduct(productId), HttpStatus.OK);
    }

}
