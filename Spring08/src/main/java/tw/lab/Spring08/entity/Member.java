package tw.lab.Spring08.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    private Integer id;
    private String email, passwd, name;
}
