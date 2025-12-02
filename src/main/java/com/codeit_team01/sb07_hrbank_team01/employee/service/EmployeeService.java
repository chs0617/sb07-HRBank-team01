package com.codeit_team01.sb07_hrbank_team01.employee.service;

import com.codeit_team01.sb07_hrbank_team01.employee.dto.request.EmployeeCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.employee.dto.response.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeCreateRequestDto employeeRequestDto);
    EmployeeResponseDto updateEmployee(EmployeeCreateRequestDto employeeRequestDto);
    void deleteEmployee(Long id);
    EmployeeResponseDto getEmployee(Long id);
}
