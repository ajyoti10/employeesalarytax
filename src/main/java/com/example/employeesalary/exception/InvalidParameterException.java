package com.example.employeesalary.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.example.employeesalary.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class InvalidParameterException extends DemoException{
    private static final Logger logger = LoggerFactory.getLogger(InvalidParameterException.class);

    private static final long serialVersionUID = -6406289686808222972L;

    public InvalidParameterException(Object dto, List<ErrorDTO> errors) {
        super(dto, errors.toArray(new ErrorDTO[] {}));
        logger.info("InvalidParameterException call");
    }

}
