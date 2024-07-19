package com.example.employeesalary.entity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NotNull()
    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "doj")
    private LocalDateTime doj;

    @Column(name = "salary")
    private Double salary;

    public Employee() {
    }

    // Parameterized constructor
    public Employee(Integer id, String employeeId, String firstName, String lastName, String email, LocalDateTime doj, Double salary) {
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName =lastName;
        this.email = email;
        this.doj = doj;
        this.salary = salary;
    }

}
