package tw.lab.Spring04.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring04.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
