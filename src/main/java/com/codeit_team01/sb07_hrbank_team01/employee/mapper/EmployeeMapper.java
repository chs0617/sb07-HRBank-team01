package com.codeit_team01.sb07_hrbank_team01.employee.mapper;

import com.codeit_team01.sb07_hrbank_team01.employee.dto.response.EmployeeResponseDto;
import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    public EmployeeResponseDto toDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getEmployeeNo(),
                employee.getDepartment().getId(),
                employee.getDepartment(),
                employee.getHireDate(),
                employee.getJobPosition(),
                employee.getStatus()
        );
    }
}
