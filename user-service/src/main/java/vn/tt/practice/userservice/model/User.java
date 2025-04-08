package vn.tt.practice.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "userDB")
@Builder
@Data
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private Boolean role;

}
