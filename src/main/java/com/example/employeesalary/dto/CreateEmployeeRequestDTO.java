package com.example.employeesalary.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequestDTO {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private List<Integer> phoneNumbers;
    private String doj;
    private Double salary;
	
}
