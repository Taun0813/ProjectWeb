package vn.tt.practice.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class ProductDTO {
    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Price cannot be blank")
    private double price;
    private String description;
    private String image;
    private boolean checkToCart;
    private Integer rating;
    private Integer quantity;
}
