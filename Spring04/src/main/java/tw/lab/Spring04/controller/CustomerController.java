package tw.lab.Spring04.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.lab.Spring04.dto.CustomerDto;
import tw.lab.Spring04.dto.OrderDetailDto;
import tw.lab.Spring04.dto.OrderDto;
import tw.lab.Spring04.entity.Customer;
import tw.lab.Spring04.entity.Order;
import tw.lab.Spring04.entity.OrderDetail;
import tw.lab.Spring04.repo.CustomerRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @Operation(summary = "查詢客戶訂單", description = "可執行")
    @GetMapping("/v1/{id}")
    public ResponseEntity<Customer> test1(
            @Parameter(description = "輸入客戶編號") @PathVariable String id) {
        return ResponseEntity.ok(customerRepo.findById(id).orElse(null));
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Customer> test2(@PathVariable String id) {

        return ResponseEntity.ok(customerRepo.findByCustomerId(id).orElse(null));
    }

    @GetMapping("/v3/{id}")
    public ResponseEntity<CustomerDto> test3(@PathVariable String id) {
        Customer c = customerRepo.findById(id).orElse(null);
        List<Order> orders = c.getOrders();

        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> details = order.getOrderdetails();

            ArrayList<OrderDetailDto> detailDtos = new ArrayList<>();
            for (OrderDetail detail : details) {
                detailDtos.add(new OrderDetailDto(
                        detail.getUnitPrice(),
                        detail.getQuantity(),
                        detail.getProduct().getProductName()));
            }
            orderDtos.add(new OrderDto(order.getId(), order.getOrderdate(), detailDtos));
        }
        CustomerDto cDto = new CustomerDto(c.getCustomerid(), c.getCompanyName(), orderDtos);

        return ResponseEntity.ok(cDto);
    }

    private CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerid(),
                customer.getCompanyName(),
                customer.getOrders().stream().map(this::toOrderDto).toList());
    }

    private OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderdate(),
                order.getOrderdetails().stream().map(this::toOrderDetailDto).toList());
    }

    private OrderDetailDto toOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity(),
                orderDetail.getProduct().getProductName());
    }

    @GetMapping("/v4/{id}")
    public ResponseEntity<CustomerDto> test4(@PathVariable String id) {
        Optional<Customer> opt = customerRepo.findById(id);
        if (opt.isPresent()) {
            Customer c = opt.get();
            CustomerDto cDto = toCustomerDto(c);
            return ResponseEntity.ok(cDto);
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @GetMapping("/v5/{id}")
    public ResponseEntity<CustomerDto> test5(@PathVariable String id) {
        return customerRepo.findById(id)
                .map(this::toCustomerDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        // orElseGet() :cast on isEmpty => delay cast
        // orElse() => cast right now
    }

}
