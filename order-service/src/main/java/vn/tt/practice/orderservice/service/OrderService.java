package vn.tt.practice.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.tt.practice.orderservice.dto.Payload;
import vn.tt.practice.orderservice.mapper.OrderMapper;
import vn.tt.practice.orderservice.model.Order;
import vn.tt.practice.orderservice.producer.OrderEventProducer;
import vn.tt.practice.orderservice.repository.OrderRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final MongoTemplate mongoTemplate;
    private final OrderEventProducer orderEventProducer;

    public Payload placeOrder(Payload payload) {
        payload.setStatus("pending");

        String message = "Order placed successfully with id: " + payload.getId();
        orderEventProducer.sendOrderEvent(message, payload.getUser_id());
        return orderMapper.toDTO(orderRepo.save(orderMapper.toEntity(payload)));

    }

    public List<Payload> findByUserId(String user_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id));

        List<Order> orders = mongoTemplate.find(query, Order.class);

        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("Order not found with User ID: " + user_id);
        }

        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Payload cancelOrder(String id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        order.setStatus("CANCELED");
        return orderMapper.toDTO(orderRepo.save(order));

    }
}
