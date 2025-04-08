package vn.tt.practice.be.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.be.models.Order;
import vn.tt.practice.be.repositories.OrderRepo;
import vn.tt.practice.be.services.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepo orderRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderRepo.findById(id).orElse(null));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderRepo.save(order));
    }
}
