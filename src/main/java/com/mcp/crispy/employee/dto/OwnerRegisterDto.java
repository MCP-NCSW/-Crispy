package com.mcp.crispy.employee.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerRegisterDto {
    private int empNo;
    private String empId;
    private String empPw;
    private String empName;
    private String empEmail;
    private String empPhone;
    private int posNo;
    private int frnNo;
}
