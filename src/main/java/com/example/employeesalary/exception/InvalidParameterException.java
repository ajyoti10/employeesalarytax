package com.example.employeesalary.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.example.employeesalary.dto.ErrorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidParameterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvalidParameterException.class);

    private List<ErrorDTO> errors;

    public InvalidParameterException(List<ErrorDTO> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }


}
