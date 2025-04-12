package vn.tt.practice.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.productservice.dto.ProductDTO;
import vn.tt.practice.productservice.model.Product;
import vn.tt.practice.productservice.repository.ProductRepo;
import vn.tt.practice.productservice.service.ProductService;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/products")
//@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;
    private final ProductRepo productRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {

        return ResponseEntity.ok(productRepo.findById(id).orElse(null));
    }



    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {

        long start = System.currentTimeMillis();

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.getAllProducts(pageable);

        long end = System.currentTimeMillis();
        System.out.println("⏱️ Total response time: " + (end - start) + " ms");

        return ResponseEntity.ok(products);
    }

    @PostMapping("")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
//        return ResponseEntity.ok(productRepo.save(Product.builder()
//                                                        .name(productDTO.getName())
//                                                        .product_image(productDTO.getProduct_image())
//                                                        .description(productDTO.getDescription())
//                                                        .price(productDTO.getPrice())
//                                                        .build()));
        productService.createProduct(productDTO);
        return ResponseEntity.ok().body("Add Product successfull");
    }

    @PutMapping("/{id}/add-to-cart")
    public ResponseEntity<ProductDTO> addToCart(@PathVariable String id) {
        return ResponseEntity.ok(productService.addToCart(id));
    }

    @PutMapping("/{id}/remove-from-cart")
    public ResponseEntity<ProductDTO> removeFromCart(@PathVariable String id) {
        return ResponseEntity.ok(productService.removeFromCart(id));
    }



//    @PutMapping("/cart/clear")
//    public ResponseEntity<ProductDTO> clearCart() {
//        return ResponseEntity.ok(productService.clearCart);
//    }

}
