package com.example.employeesalary.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.example.employeesalary.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class DemoException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(InvalidParameterException.class);
    private static final long serialVersionUID = 7932731402935556075L;

    protected final Object dto;
    protected final List<ErrorDTO> errors;

    public DemoException(Object dto, ErrorDTO... errors) {
        this.dto = dto;
        this.errors = List.of(errors);
        logger.info("DemoException call" + this.errors);
    }
}