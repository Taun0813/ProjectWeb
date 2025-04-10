package vn.tt.practice.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.tt.practice.productservice.dto.ProductDTO;
import vn.tt.practice.productservice.mapper.ProductMapper;
import vn.tt.practice.productservice.model.Product;
import vn.tt.practice.productservice.repository.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepo.findAll(pageable);
        return products.map(productMapper::toDTO);
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




}
