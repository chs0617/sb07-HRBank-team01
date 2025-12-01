package com.codeit_team01.sb07_hrbank_team01.department.response;

import java.time.LocalDate;
import java.util.UUID;

public record DepartmentDto(
        UUID id,
        String name,
        String description,
        LocalDate establishDate,
        int employeeCount

) {
}
