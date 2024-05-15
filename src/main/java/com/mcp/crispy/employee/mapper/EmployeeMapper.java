package com.mcp.crispy.employee.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.crispy.employee.dto.EmployeeDto;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface EmployeeMapper {

	void insertEmployee(EmployeeDto employee);

	@Select("SELECT EMP_ID, EMP_PW FROM EMPLOYEE_T WHERE EMP_ID = #{empId}")
	EmployeeDto findByUsername(String username);
}
