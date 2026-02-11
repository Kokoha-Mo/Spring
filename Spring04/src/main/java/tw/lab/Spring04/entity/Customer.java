package tw.lab.Spring04.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @Column(name = "CustomerID")
    private String customerid;

    @Column(name = "CompanyName")
    private String companyName;
    // ----------------------
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
