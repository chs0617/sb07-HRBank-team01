package com.codeit_team01.sb07_hrbank_team01.department.response;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record DepartmentResponseDto(
        Long id,
        String name,
        String description,
        Instant establishDate,
        int employeeCount
) {
    public  static DepartmentResponseDto from(Department department, int employeeCount){
        return new DepartmentResponseDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getEstablishDate(),
                employeeCount
        );
    }

}
