package ph.matt.productmsvc.util;

import ph.matt.productmsvc.controller.dto.ProductDto;
import ph.matt.productmsvc.controller.dto.ProductNoIdDto;
import ph.matt.productmsvc.entity.Product;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Product dtoToEntity(ProductNoIdDto dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getBrand());
    }

    public static Product dtoToEntity(ProductDto dto) {
        return new Product(dto.getId(), dto.getName(), dto.getDescription(), dto.getPrice(), dto.getBrand());
    }

    public static ProductNoIdDto entityToProductNoIdDto(Product product) {
        return new ProductNoIdDto(product.getName(), product.getDescription(), product.getPrice(),
                product.getBrand());
    }

    public static ProductDto entityToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getBrand());
    }

}
