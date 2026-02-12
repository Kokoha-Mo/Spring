package tw.lab.Spring04.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @Column(name = "OrderID")
    private int id;

    @Column(name = "OrderDate")
    private Date orderdate;
    // -----------------------
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    @JsonBackReference
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderdetails;

}
