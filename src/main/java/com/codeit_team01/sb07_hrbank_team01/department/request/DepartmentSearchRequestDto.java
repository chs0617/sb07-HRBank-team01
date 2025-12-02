package com.codeit_team01.sb07_hrbank_team01.department.request;

import jakarta.validation.constraints.*;
import org.hibernate.query.SortDirection;


public record DepartmentSearchRequestDto(

        String nameOrDescription,
        Long idAfter,
        String cursor,
        Integer size,
        String sortField,
        String sortDirection
) {

}
