package com.example.spring1.apis;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.Order;
import com.example.spring1.dto.OrderDetail;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Lab12")
public class Lab12 {
    @Autowired
    @Qualifier("northJdbc")
    private NamedParameterJdbcTemplate northJdbc;

    @GetMapping("/test1")
    public void test1() {
        String sql = """
                SELECT EmployeeID,FirstName,LastName,Title
                FROM employees
                """;
        List<Map<String, Object>> maps = northJdbc.queryForList(sql, new HashMap<>());
        System.out.println(maps.size());
    }

    @GetMapping("/orders/{orderId}")
    public Order test2(@PathVariable Integer orderId) {
        String sql = """
                SELECT
                o.OrderID,o.OrderDate,
                p.ProductName,od.UnitPrice,od.Quantity
                FROM orders o
                JOIN orderdetails od ON o.OrderID = od.OrderID
                JOIN products p ON od.ProductID = p.ProductID
                WHERE o.OrderID = :orderId
                """;
        Map<String, Integer> params = new HashMap<>();
        params.put("orderId", orderId);

        Order order = new Order();

        List<Map<String, Object>> details = northJdbc.queryForList(sql, params);
        order.setOrderId((Integer) details.get(0).get("OrderId"));
        order.setOrderDate(((LocalDateTime) details.get(0).get("OrderDate")).toString());
        // System.out.println(details.size());
        for (Map<String, Object> detail : details) {
            OrderDetail od = new OrderDetail();
            od.setPname(detail.get("ProductName").toString());
            od.setPrice(Double.parseDouble(detail.get("UnitPrice").toString()));
            od.setQty(Integer.parseInt(detail.get("Quantity").toString()));
            order.getOdetails().add(od);
        }

        return order;
    }

}
