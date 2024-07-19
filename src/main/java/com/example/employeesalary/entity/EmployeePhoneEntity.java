package com.example.employeesalary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@Entity
@ToString
@Table(name = "employee_phone")
public class EmployeePhoneEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "phone_number")
    private String phoneNumber;
}
