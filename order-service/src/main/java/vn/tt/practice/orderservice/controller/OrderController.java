package vn.tt.practice.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.orderservice.dto.Payload;
import vn.tt.practice.orderservice.mapper.OrderMapper;
import vn.tt.practice.orderservice.repository.OrderRepo;
import vn.tt.practice.orderservice.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
@Slf4j
//@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @PostMapping("/place-order")
    public ResponseEntity<Payload> placeOrder(@RequestBody Payload request) {
        return ResponseEntity.ok().body(orderService.placeOrder(request));
    }


    @GetMapping("/{user_id}/get-orders")
    public ResponseEntity<List<Payload>> getOrderById(@PathVariable String user_id) {
        return ResponseEntity.ok().body(orderService.findByUserId(user_id));
    }

    @PostMapping("/cancel-order")
    public ResponseEntity<Payload> cancelOrder(@RequestBody Payload payload) {
        return ResponseEntity.ok().body(orderService.cancelOrder(payload.getId()));

    }
}
