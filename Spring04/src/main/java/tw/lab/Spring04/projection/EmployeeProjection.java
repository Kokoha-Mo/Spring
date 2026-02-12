package tw.lab.Spring04.projection;

import java.util.List;

import tw.lab.Spring04.entity.Order;

/*
projection的方法名稱需對應到Entity
 */
public interface EmployeeProjection {
    String getLastName();

    String getFirstName();

    String getTitle();

    List<Order> getOrders();
}
