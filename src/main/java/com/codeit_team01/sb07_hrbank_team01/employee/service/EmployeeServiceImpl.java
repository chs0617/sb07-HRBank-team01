package com.codeit_team01.sb07_hrbank_team01.employee.service;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.employee.dto.request.EmployeeCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.employee.dto.response.EmployeeResponseDto;
import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import com.codeit_team01.sb07_hrbank_team01.employee.mapper.EmployeeMapper;
import com.codeit_team01.sb07_hrbank_team01.employee.repository.EmployeeRepository;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateRequestDto employeeCreateRequestDto) {
        Objects.requireNonNull(employeeCreateRequestDto, "요청이 null일 수 없습니다.");

        if (employeeRepository.existsByEmailIgnoreCase(employeeCreateRequestDto.email())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        Department department = departmentRepository.findById(employeeCreateRequestDto.departmentId())
                .orElseThrow(() -> new NoSuchElementException("부서를 찾을 수 없습니다."));

        File profile = null;
        if (employeeCreateRequestDto.profileImageId() != null) {
            profile = fileRepository.findById(employeeCreateRequestDto.profileImageId())
                    .orElseThrow(() -> new NoSuchElementException("프로필 파일을 찾을 수 없습니다."));
        }

        String employeeNo = createEmployeeNo.generate();

        Employee newEmployee = Employee.builder()
                .name(employeeCreateRequestDto.name())
                .email(employeeCreateRequestDto.email())
                .jobPosition(employeeCreateRequestDto.position())
                .department(department)
                .employeeNo(employeeNo)
                .profile(profile)
                .hireDate(employeeCreateRequestDto.hireDate())
                .build();

        Employee save = employeeRepository.save(newEmployee);
        return employeeMapper.toDto(save);
    }

    @Override
    public EmployeeResponseDto updateEmployee(EmployeeCreateRequestDto employeeRequestDto) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public EmployeeResponseDto getEmployee(Long id) {
        return null;
    }
}
