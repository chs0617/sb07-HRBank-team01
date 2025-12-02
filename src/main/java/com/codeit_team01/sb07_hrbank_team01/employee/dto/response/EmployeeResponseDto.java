package com.codeit_team01.sb07_hrbank_team01.employee.dto.response;

import java.time.Instant;

public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String employeeNumber,
        Long departmentId,
        String departmentName,
        String position,
        Instant hireDate,
        String status,
        Long profileImageId
) {
}
