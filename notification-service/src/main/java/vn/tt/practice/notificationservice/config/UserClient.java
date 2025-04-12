package vn.tt.practice.notificationservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {
    private final RestTemplate restTemplate;

    public UserClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getEmailByUserId(String userId) {
        try {
            return restTemplate.getForObject("http://localhost:8082/v1/api/user/" + userId + "/email", String.class);
        } catch (Exception e) {
            return null;
        }
    }


}
