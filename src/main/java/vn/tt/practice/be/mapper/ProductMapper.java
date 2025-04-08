package vn.tt.practice.be.mapper;

import org.springframework.stereotype.Component;
import vn.tt.practice.be.dto.ProductDTO;
import vn.tt.practice.be.models.Product;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .productImage(product.getProductImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    public Product toEntity(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .productImage(dto.getProductImage())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }


}
