package com.codeit_team01.sb07_hrbank_team01.department.request;


import java.time.LocalDate;

public record DepartmentUpdateRequestDto(
        String name,
        String description,
        LocalDate establishedDate
) {
}
