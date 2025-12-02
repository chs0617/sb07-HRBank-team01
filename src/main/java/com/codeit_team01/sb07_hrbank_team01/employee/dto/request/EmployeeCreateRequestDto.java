package com.codeit_team01.sb07_hrbank_team01.employee.dto.request;

import java.time.Instant;

public record EmployeeCreateRequestDto(
        String name,
        String email,
        Long departmentId,
        String position,
        Instant hireDate,
        Long profileImageId
) {
}
