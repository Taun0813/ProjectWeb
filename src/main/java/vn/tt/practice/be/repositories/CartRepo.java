package vn.tt.practice.be.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.tt.practice.be.models.Cart;

public interface CartRepo extends MongoRepository<Cart, String> {
}
