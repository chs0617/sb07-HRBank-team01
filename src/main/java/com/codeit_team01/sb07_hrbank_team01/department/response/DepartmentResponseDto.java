package com.codeit_team01.sb07_hrbank_team01.department.response;

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
}
