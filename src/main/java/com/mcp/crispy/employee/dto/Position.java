package com.mcp.crispy.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {
    OWNER(0, "OWNER"),   // 0: 점주
    MANAGER(1 ,"MANAGER"), // 1: 메니저
    EMPLOYEE(2 ,"EMPLOYEE"); // 2: 직원

    private final int code;
    private final String description;
}