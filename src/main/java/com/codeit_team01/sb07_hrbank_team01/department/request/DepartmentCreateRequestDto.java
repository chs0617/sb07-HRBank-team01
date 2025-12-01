package com.codeit_team01.sb07_hrbank_team01.department.request;

import java.time.Instant;

public record DepartmentCreateRequestDto(
        String name,
        String description,
        Instant establishedDate
) {



}
