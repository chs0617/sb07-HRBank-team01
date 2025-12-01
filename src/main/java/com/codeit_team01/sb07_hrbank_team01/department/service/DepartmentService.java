package com.codeit_team01.sb07_hrbank_team01.department.service;

import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request);
    DepartmentResponseDto updateDepartment(UUID departmentId, DepartmentUpdateRequestDto request);
    void deleteDepartment(UUID departmentId);
    DepartmentResponseDto getDepartment(UUID departmentId);
    Slice<DepartmentResponseDto> searchDepartment(DepartmentSearchRequestDto condition);

}
