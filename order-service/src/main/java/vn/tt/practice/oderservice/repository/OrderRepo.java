package vn.tt.practice.oderservice.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.tt.practice.oderservice.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends MongoRepository<Order, String> {
//    Optional<Order> findById(String id);




}

