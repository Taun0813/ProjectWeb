package vn.tt.practice.paymentservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String paymentUrl;
}
