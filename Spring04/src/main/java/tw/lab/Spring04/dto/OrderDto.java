package tw.lab.Spring04.dto;

import java.util.Date;
import java.util.List;

// import lombok.AllArgsConstructor;
// import lombok.Getter;

// @Getter
// @AllArgsConstructor
// public class OrderDto {
//     private Integer orderId;
//     private Date orderDate;
//     private List<OrderDetailDto> details;
// }

public record OrderDto(
        Integer orderId,
        Date orderDate,
        List<OrderDetailDto> details) {
}
