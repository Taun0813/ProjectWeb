package vn.tt.practice.be.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "cartDB")
@Builder
@Data
@AllArgsConstructor
public class Cart {
    @Id
    private String id;

    private String userId;
    private List<String> productIds;
}
