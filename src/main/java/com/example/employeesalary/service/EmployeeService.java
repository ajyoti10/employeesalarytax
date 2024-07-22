package com.example.employeesalary.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.employeesalary.entity.Employee;
import com.example.employeesalary.entity.EmployeePhone;
import com.example.employeesalary.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.employeesalary.repository.EmployeeRepository;
import com.example.employeesalary.repository.EmployeePhoneRepository;
import com.example.employeesalary.dto.*;
import com.example.employeesalary.enums.ErrorCode;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired EmployeeRepository employeeRepository;
    @Autowired EmployeePhoneRepository employeePhoneRepository;

    public BaseResponseDTO createEmployee(final CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        logger.info("createEmployee API logic start  createEmployee: " +createEmployeeRequestDTO);
        employeeValidation(createEmployeeRequestDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Employee employeeEntity = Employee.builder()
                .employeeId(createEmployeeRequestDTO.getEmployeeId())
                .doj(LocalDateTime.parse(createEmployeeRequestDTO.getDoj(),formatter))
                .firstName(createEmployeeRequestDTO.getFirstName())
                .lastName(createEmployeeRequestDTO.getLastName())
                .email(createEmployeeRequestDTO.getEmail())
                .salary(createEmployeeRequestDTO.getSalary())
                .build();
        employeeRepository.save(employeeEntity);
        List<Integer> phoneNumbers = createEmployeeRequestDTO.getPhoneNumbers();
        logger.info("createEmployee API getPhoneNumbers   : " + createEmployeeRequestDTO.getPhoneNumbers());
        for(Integer phone : phoneNumbers) {
        	EmployeePhone employeePhone = EmployeePhone.builder()
        			                      .phoneNumber(phone)
        			                      .employee(employeeEntity)
        			                      .build();
        	employeePhoneRepository.save(employeePhone);
        }
        final BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setStatus("success");
        baseResponseDTO.setResponseMessage("Employee created successfully");
        return  baseResponseDTO;

    }

    private void employeeValidation(final CreateEmployeeRequestDTO createEmployeeRequestDTO){
        List<ErrorDTO> errors = new ArrayList<>();
        if(!StringUtils.hasLength(createEmployeeRequestDTO.getEmployeeId())){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1000));
        }
        if(!StringUtils.hasLength(createEmployeeRequestDTO.getFirstName())){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1001));
        }
        if(!StringUtils.hasLength(createEmployeeRequestDTO.getLastName())){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1003));
        }
        if(!StringUtils.hasLength(createEmployeeRequestDTO.getEmail())){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1003));
        }
        if(!StringUtils.hasLength(createEmployeeRequestDTO.getDoj())){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1004));
        }
        if(createEmployeeRequestDTO.getSalary() == null || createEmployeeRequestDTO.getSalary() < 0 ){
            errors.add(ErrorDTO.build(ErrorCode.EMP_1005));
        }
        logger.info("employeeValidation API logic start   : "+errors);
        if(!errors.isEmpty()) {
        	logger.info("employeeValidation API logic start   : "+errors);
        throw new InvalidParameterException(errors );
        }
        
    }

    public BaseResponseDTO getTaxDeductions(){
        logger.info("getTaxDeductions API logic start   : ");
        List<TaxDeductionResponseDTO> deductions = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        logger.info("getTaxDeductions emp list   : "+employeeList);
        for(Employee employee : employeeList){
            double yearlySalary = calculateYearlySalary(employee);
            double taxAmount = calculateTaxAmount(yearlySalary);
            double ceseAmount = calculateCessAmount(yearlySalary);
            TaxDeductionResponseDTO taxDeductionResponseDTO = TaxDeductionResponseDTO.builder()
                    .employeeId(employee.getEmployeeId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .yearlySalary(yearlySalary)
                    .taxAmount(taxAmount)
                    .cessAmount(ceseAmount)
                    .build();
            deductions.add(taxDeductionResponseDTO);
        }
        logger.info("getTaxDeductions deductions   : "+deductions);
        final BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setStatus("success");
        baseResponseDTO.setResponseMessage("Employee tax deduction");
        baseResponseDTO.setData(deductions);
        return  baseResponseDTO;

    }

    private double calculateYearlySalary(Employee employee) {
        LocalDateTime doj = employee.getDoj();
        LocalDateTime now = LocalDateTime.now();
        if (doj.isAfter(now)) {
            return 0;
        }

        int monthsWorked = (int) ChronoUnit.MONTHS.between(doj.withDayOfMonth(1), now.withDayOfMonth(1)) + 1;
        int daysWorkedInFirstMonth = doj.getMonthValue() - doj.getDayOfMonth() + 1;
        double lossOfPayInFirstMonth = (employee.getSalary() / 30) * (doj.getDayOfMonth() - 1);
        double firstMonthSalary = employee.getSalary() - lossOfPayInFirstMonth;

        return firstMonthSalary + (employee.getSalary() * (monthsWorked - 1));
    }

    private double calculateTaxAmount(double yearlySalary) {
        double tax = 0;
        if (yearlySalary > 250000) {
            double taxableAmount = Math.min(250000, yearlySalary - 250000);
            tax += taxableAmount * 0.05;
        }
        if (yearlySalary > 500000) {
            double taxableAmount = Math.min(500000, yearlySalary - 500000);
            tax += taxableAmount * 0.10;
        }
        if (yearlySalary > 1000000) {
            double taxableAmount = yearlySalary - 1000000;
            tax += taxableAmount * 0.20;
        }
        return tax;
    }

    private double calculateCessAmount(double yearlySalary) {
        double cessAmount = 0;

        if (yearlySalary > 2500000) {
            cessAmount = (yearlySalary - 2500000) * 0.02;
        }

        return cessAmount;
    }

}
