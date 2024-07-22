package com.example.employeesalary.controller;

import com.example.employeesalary.dto.BaseResponseDTO;
import com.example.employeesalary.dto.CreateEmployeeRequestDTO;

import com.example.employeesalary.exception.InvalidParameterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public interface EmployeeController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDTO> createEmployee(@RequestBody CreateEmployeeRequestDTO createEmployeeRequestDTO);

    @GetMapping("tax-deduction")
    public ResponseEntity<BaseResponseDTO> getTaxDeductions();


}
