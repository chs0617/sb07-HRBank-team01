package com.codeit_team01.sb07_hrbank_team01.department.request;



public record DepartmentSearchRequestDto(

        String nameOrDescription,
        Long idAfter,
        String cursor,
        Integer size,
        String sortField,
        String sortDirection
) {

}
