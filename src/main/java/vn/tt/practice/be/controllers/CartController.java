package vn.tt.practice.be.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.be.models.Cart;
import vn.tt.practice.be.models.Order;
import vn.tt.practice.be.repositories.CartRepo;
import vn.tt.practice.be.repositories.OrderRepo;
import vn.tt.practice.be.services.CartService;
import vn.tt.practice.be.services.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartRepo cartRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable String id) {
        return ResponseEntity.ok(cartRepo.findById(id).orElse(null));
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartRepo.save(cart));
    }
}
