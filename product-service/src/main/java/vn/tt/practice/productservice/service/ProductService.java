package vn.tt.practice.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.tt.practice.productservice.dto.ProductDTO;
import vn.tt.practice.productservice.mapper.ProductMapper;
import vn.tt.practice.productservice.repository.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(productMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        return productMapper.toDTO(productRepo.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO addToCart(String id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }else {
            return productMapper.toDTO(productRepo.save(productRepo.findById(id).get()));
        }
    }

    public ProductDTO removeFromCart(String id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }else {
            productRepo.deleteById(id);
        }
        return null;
    }

//    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
//        if (!productRepo.existsById(id)) {
//            throw new RuntimeException("Product not found");
//        }
//    }



}
