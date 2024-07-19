package com.example.employeesalary.enums;

public enum ErrorCode {
    EMP_1000("Employee id is mandtory!", ""),
    EMP_1001("First Name is mandtory!", ""),
    EMP_1002("Email is mandtory!", ""),
    EMP_1003("Salary is mandtory!!", ""),
    EMP_1004("Email is invalid!", ""),
    EMP_1007("Salery is mandtory","");
    ;

    private final String message;
    private final String attributeCode;

    private ErrorCode(String message, String attributeCode) {
        this.message = message;
        this.attributeCode = attributeCode;
    }

    public String getMessage() {
        return message;
    }

    public String getAttributeCode() {
        return attributeCode;
    }
}
