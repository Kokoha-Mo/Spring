package tw.lab.Spring04.dto;

import java.util.List;

// import lombok.AllArgsConstructor;
// import lombok.Getter;

// @Getter
// @AllArgsConstructor
// public class CustomerDto {
//     private String customerId;
//     private String companyName;
//     private List<OrderDto> orders;
// }

public record CustomerDto(
        String customerId,
        String companyName,
        List<OrderDto> orders) {
}
