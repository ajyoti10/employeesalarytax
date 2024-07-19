package com.example.employeesalary.controller;

import com.example.employeesalary.dto.BaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPIController {

    @GetMapping("v1/api/getdetail")
    public ResponseEntity<BaseResponseDTO> testAPI(){
        BaseResponseDTO baseResponseDTO = BaseResponseDTO.builder().status("success").responseMessage("Get Daetil API").build();
        return  ResponseEntity.ok(baseResponseDTO);

    }
}
