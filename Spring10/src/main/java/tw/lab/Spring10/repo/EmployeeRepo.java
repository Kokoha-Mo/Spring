package tw.lab.Spring10.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.lab.Spring10.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>,
        JpaSpecificationExecutor<Employee> {

    @Query(value = """
                SELECT * FROM employees WHERE Salary < :high
            """, nativeQuery = true)
    List<Employee> test0(@Param("high") Float high);

    // SQL
    @NativeQuery(value = """
                SELECT * FROM employees WHERE Salary < :high
            """)
    List<Employee> test1(@Param("high") Float high);

    // JPQL
    @Query(value = """
            SELECT e
            FROM Employee e
            WHERE e.salary < :high
            """)
    List<Employee> test2(@Param("high") Float high);

    List<Employee> findBySalaryLessThan(Float high);

    List<Employee> findBySalaryGreaterThanAndFirstNameContaining(Float high, String name);

}
