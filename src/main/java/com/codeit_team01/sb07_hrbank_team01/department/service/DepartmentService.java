package com.codeit_team01.sb07_hrbank_team01.department.service;

import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentPageResponseDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request);
    DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateRequestDto request);
    void deleteDepartment(Long departmentId);
    DepartmentResponseDto getDepartment(Long departmentId);
    DepartmentPageResponseDto<DepartmentResponseDto> searchDepartment(DepartmentSearchRequestDto condition);

}
