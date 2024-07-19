package com.example.employeesalary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.employeesalary.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

    private String errorCode;
    private String message;
    private String attributeCode;

    public static ErrorDTO build(final ErrorCode errorCode, final String... params) {
        return ErrorDTO.builder()
                .errorCode(errorCode.name())
                .message(String.format(errorCode.getMessage(), (Object[]) params))
                .attributeCode(errorCode.getAttributeCode())
                .build();

    }
}
