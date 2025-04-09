package vn.tt.practice.oderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.oderservice.dto.Payload;
import vn.tt.practice.oderservice.mapper.OrderMapper;
import vn.tt.practice.oderservice.model.Order;
import vn.tt.practice.oderservice.repository.OrderRepo;
import vn.tt.practice.oderservice.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
@Slf4j
@CrossOrigin(origins = "*")
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
