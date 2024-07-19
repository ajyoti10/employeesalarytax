package com.example.employeesalary;

import com.example.employeesalary.controller.TestAPIController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeSalaryApplicationTests {

	@Test
	void contextLoads() {
		TestAPIController testAPIController = new TestAPIController();
		testAPIController.testAPI();
	}

}
