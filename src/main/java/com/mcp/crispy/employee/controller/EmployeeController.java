package com.mcp.crispy.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crispy/employee")
public class EmployeeController {


	@GetMapping("/find/username")
	public String findUsername() {
		return "employee/find-username";
	}

	@GetMapping("/find/password")
	public String findPassword() {
		return "employee/find-password";
	}

	@GetMapping("/change/password")
	public String changePassword() {
		return "employee/change-password";
	}

	@GetMapping("/find/username/result")
	public String findUsernameResult() {
		return "employee/find-username-result";
	}

	@GetMapping("/profile")
	public String getEmployee() {
		return "employee/employee-profile";
	}

}