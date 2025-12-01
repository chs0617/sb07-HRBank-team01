package com.codeit_team01.sb07_hrbank_team01.department.service;

import java.util.UUID;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentCreateRequest request);
    DepartmentDto updateDepartment(UUID departmentId, DepartmentUpdateRequest request);
    void deleteDepartment(UUID departmentId);
    DepartmentDto getDepartment(UUID departmentId);
    Slice<DepartmentDto> searchDepartment(DepartmentSearchCondition condition);

}
