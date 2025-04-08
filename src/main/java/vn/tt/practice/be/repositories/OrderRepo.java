package vn.tt.practice.be.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.tt.practice.be.models.Order;

public interface OrderRepo extends MongoRepository<Order, String> {
}
