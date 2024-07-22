package com.example.employeesalary.controller.impl;

import com.example.employeesalary.controller.EmployeeController;
import com.example.employeesalary.dto.BaseResponseDTO;
import com.example.employeesalary.dto.CreateEmployeeRequestDTO;
import com.example.employeesalary.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeesalary.service.EmployeeService;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class EmployeeControllerImpl implements EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerImpl.class);
    @Autowired EmployeeService employeeService;
    @Override
    public ResponseEntity<BaseResponseDTO> createEmployee(final CreateEmployeeRequestDTO createEmployeeRequestDTO){
    	//createEmployeeRequestDTO.
       return ResponseEntity.ok(employeeService.createEmployee(createEmployeeRequestDTO));
    }
    @Override
    public ResponseEntity<BaseResponseDTO> getTaxDeductions(){
        return ResponseEntity.ok(employeeService.getTaxDeductions());
    }    
}
