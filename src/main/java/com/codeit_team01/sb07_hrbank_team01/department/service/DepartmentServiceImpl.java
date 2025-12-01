package com.codeit_team01.sb07_hrbank_team01.department.service;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.department.repository.DepartmentRepository;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request) {
        return null;
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateRequestDto request) {
        return null;
    }

    @Override
    public void deleteDepartment(Long departmentId) {

    }

    @Override
    public DepartmentResponseDto getDepartment(Long departmentId) {
        return null;
    }

    @Override
    public Slice<DepartmentResponseDto> searchDepartment(DepartmentSearchRequestDto condition) {
        return null;
    }
}
