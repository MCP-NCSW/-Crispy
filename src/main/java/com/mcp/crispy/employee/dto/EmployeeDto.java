package com.mcp.crispy.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private int empNo;
    private String empId;
    private String empPw;
    private String empName;
    private String empEmail;
    private String empPhone;
    private EmpApprovalStatus empAppr;
    private String empZip;
    private String empStreet;
    private String empDetail;
    private String empProfile;
    private String empSign;
    private int empAnnual;
    private EmpStatus empStat;
    private Date empInDt;
    private Date empOutDt;
    private Date createDt;
    private Date modifyDt;
    private Position posNo;
}
