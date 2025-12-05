package com.codeit_team01.sb07_hrbank_team01.department.service;

import com.codeit_team01.sb07_hrbank_team01.common.dto.response.PageResponseDto;
import com.codeit_team01.sb07_hrbank_team01.common.mapper.PageResponseMapper;
import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.department.mapper.DepartmentResponseMapper;
import com.codeit_team01.sb07_hrbank_team01.department.repository.DepartmentRepository;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import com.codeit_team01.sb07_hrbank_team01.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PageResponseMapper pageResponseMapper;
    private final DepartmentResponseMapper departmentResponseMapper;

    @Override
    @Transactional
    public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto request) {
        boolean exist = departmentRepository.existsByName(request.name());
        if(exist){
            throw new IllegalArgumentException("이미 존재하는 부서 이름입니다: " + request.name());
        }
        Department department = Department.of(request.name(), request.description(), request.establishedDate());

        Department save = departmentRepository.save(department);


        return departmentResponseMapper.toDto(save, 0);
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

        return departmentResponseMapper.toDto(department, employeeCount);
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

        return departmentResponseMapper.toDto(department, employeeCount);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponseDto<DepartmentResponseDto> searchDepartment(DepartmentSearchRequestDto req) {


        //  페이지 설정
        Pageable pageable = PageRequest.of(0,req.size());

        //  레포 호출 (QueryDSL 단일 메서드)
        Page<Department> page = departmentRepository.search(
               req.nameOrDescription(),
               req.sortField(),
               req.sortDirection(),
               req.cursor(),
               req.idAfter(),
               pageable
        );

        // 콘텐츠 매핑
        List<DepartmentResponseDto> contents = page.getContent().stream()
                .map(d -> departmentResponseMapper.toDto(d,
                        employeeRepository.countByDepartmentId(d.getId())
                ))
                .toList();

        //  nextCursor / nextIdAfter 계산
        String nextCursor = null;
        Long nextIdAfter = null;
        if (!page.isEmpty()) {
            //page.getContent().get()은 0~~n 넘버링이니
            //마지막 조회를 가지고 와야하는기 반  실제조회갯수가 page.getNumberOfElements()
            //찐 마지막넘버는 -1 로  5개를가지고왔으면 0~4니까
            Department last = page.getContent().get(page.getNumberOfElements() - 1);
            nextCursor = "establishedDate".equals(req.sortField())
                    ? last.getEstablishedDate().toString()
                    : last.getName();
            nextIdAfter = last.getId();
        }


        int size = req.size();
        int numberOfElements = page.getNumberOfElements();
        boolean hasNext = (numberOfElements == size);
        long totalElements = page.getTotalElements();

        return new PageResponseDto<>(
                contents,
                nextCursor,
                nextIdAfter,
                size,
                totalElements,
                hasNext
        );
    }




}
