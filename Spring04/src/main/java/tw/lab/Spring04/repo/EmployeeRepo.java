package tw.lab.Spring04.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring04.entity.Employee;
import tw.lab.Spring04.projection.EmployeeProjection;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<EmployeeProjection> searchByTitleStartingWith(String start);
}
