package tw.lab.Spring05.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {
    @Id
    private Long id;
    private String name;
    private String addr;
    private String tel;
}
