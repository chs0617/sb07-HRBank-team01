package com.codeit_team01.sb07_hrbank_team01.department.service;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.department.repository.DepartmentRepository;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import com.codeit_team01.sb07_hrbank_team01.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request) {
        boolean exist = departmentRepository.existsByName(request.name());
        if(exist){
            throw new IllegalArgumentException("이미 존재하는 부서 이름입니다: " + request.name());
        }
        Department department = Department.of(request.name(), request.description(), request.establishedDate());

        Department save = departmentRepository.save(department);


        return DepartmentResponseDto.from(save, 0);
    }

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateRequestDto request) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NoSuchElementException("부서를 찾을 수 없습니다: " + departmentId));

        if (!department.getName().equals(request.name())
                && departmentRepository.existsByName(request.name())) {
            throw new IllegalArgumentException("이미 존재하는 부서 이름입니다: " + request.name());
        }

        department.update(request.name(), request.description(), request.establishedDate());

        int employeeCount = (int) employeeRepository.countByDepartmentId(department.getId());

        return DepartmentResponseDto.from(department, employeeCount);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new NoSuchElementException("부서 아이디로 찾을 수 없습니다");
        }

        departmentRepository.deleteById(departmentId);

    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponseDto getDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NoSuchElementException("부서 아이디로 찾을수 없습니다"));

        int employeeCount = (int) employeeRepository.countByDepartmentId(department.getId());

        return DepartmentResponseDto.from(department, employeeCount);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<DepartmentResponseDto> searchDepartment(DepartmentSearchRequestDto request) {
        Pageable pageable = PageRequest.of(
                0,
                request.size(),
                Sort.by(request.sortDir(), request.sortColumn())
        );

        Slice<Department> slice = departmentRepository.findAllBySearchCondition(
                request.nameOrDescription(),
                request.idAfter(),
                pageable
        );

        return slice.map(dept -> {
            int employeeCount = (int) employeeRepository.countByDepartmentId(dept.getId());
            return DepartmentResponseDto.from(dept, employeeCount);
        });
    }



}
