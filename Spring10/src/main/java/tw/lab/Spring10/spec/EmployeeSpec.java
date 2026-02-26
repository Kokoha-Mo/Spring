package tw.lab.Spring10.spec;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.lab.Spring10.entity.Employee;

/*
動態組裝查詢 Dynamic Queries
*/

public class EmployeeSpec {

    public static Specification<Employee> salaryGreaterThan(Float low) {
        // return new Specification<Employee>() {

        // @Override
        // public @Nullable Predicate toPredicate(
        // Root<Employee> root,
        // CriteriaQuery<?> query,
        // CriteriaBuilder criteriaBuilder) {
        // return criteriaBuilder.greaterThan(root.get("salary"), low);
        // }
        // };

        return (root, query, builder) -> builder.greaterThan(root.get("salary"), low);
    }

    // Salary > low,FirstName Like keyword
    public static Specification<Employee> salaryAndName(Float low, String name) {
        return (root, query, builder) -> {
            Predicate salary = builder.greaterThan(root.get("salary"), low);
            Predicate firstname = builder.like(root.get("firstName"), name);
            return builder.and(salary, firstname);
        };
    }

    public static Specification<Employee> firstNameEquals(String firstName) {
        return (root, query, builder) -> firstName == null ? null : builder.equal(root.get("firstName"), firstName);
    }

    public static Specification<Employee> lastNameEquals(String lastName) {
        return (root, query, builder) -> lastName == null ? null : builder.equal(root.get("lastName"), lastName);
    }

    public static Specification<Employee> titleEquals(String title) {
        return (root, query, builder) -> title == null ? null : builder.equal(root.get("title"), title);
    }
}
