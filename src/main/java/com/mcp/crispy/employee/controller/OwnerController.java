package com.mcp.crispy.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crispy/owner")
public class OwnerController {

    @GetMapping("/employee/register")
    public String registerEmployee() {
        return "owner/employee-register";
    }

    @GetMapping("/employees")
    public String getListEmployees() {
        return "owner/employees";
    }

    @GetMapping("/employee")
    public String employee() {
        return "owner/employee-detail";
    }
}
