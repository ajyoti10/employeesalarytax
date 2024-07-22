package com.example.employeesalary.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.employeesalary.dto.ErrorDTO;
import com.example.employeesalary.exception.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<List<ErrorDTO>> handleCustomException(InvalidParameterException ex) {
        List<ErrorDTO> errorDetails = ex.getErrors();
        logger.info("GlobalExceptionHandler" + errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}