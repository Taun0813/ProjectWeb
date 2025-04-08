package vn.tt.practice.oderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.tt.practice.oderservice.dto.Payload;
import vn.tt.practice.oderservice.mapper.OrderMapper;
import vn.tt.practice.oderservice.model.Order;
import vn.tt.practice.oderservice.repository.OrderRepo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final MongoTemplate mongoTemplate;

    public Payload placeOrder(Payload payload) {
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
}
