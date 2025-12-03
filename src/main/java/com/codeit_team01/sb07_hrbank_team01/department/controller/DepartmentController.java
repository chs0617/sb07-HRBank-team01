package com.codeit_team01.sb07_hrbank_team01.department.controller;

import com.codeit_team01.sb07_hrbank_team01.common.api.ApiResponseDto;
import com.codeit_team01.sb07_hrbank_team01.common.dto.response.PageResponseDto;
import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import com.codeit_team01.sb07_hrbank_team01.department.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@Validated
public class DepartmentController {

    private DepartmentService departmentService;



    @PostMapping
    public DepartmentResponseDto createDepartment(
            @RequestBody @Valid DepartmentCreateRequestDto request){

        return departmentService.createDepartment(request);
    }

    @GetMapping(value = "/{departmentId}")
    public DepartmentResponseDto getDepartment(
            @PathVariable
            @NotNull(message = "부서 ID는 필수입니다.")
            @Positive(message = "부서 ID는 1 이상이어야 합니다.") Long departmentId){

        return departmentService.getDepartment(departmentId);

    }

    @PatchMapping(value = "/{departmentId}")
    public DepartmentResponseDto updateDepartment(
            @PathVariable
            @NotNull(message = "부서 ID는 필수입니다.")
            @Positive(message = "부서 ID는 1 이상이어야 합니다.") Long departmentId,

            @RequestBody @Valid  DepartmentUpdateRequestDto request){

        return departmentService.updateDepartment(departmentId, request);
    }

   @DeleteMapping(value = "/{departmentId}")
   public void deleteDepartment(
           @PathVariable
           @NotNull(message = "부서 ID는 필수입니다.")
           @Positive(message = "부서 ID는 1 이상이어야 합니다.") Long departmentId){

         departmentService.deleteDepartment(departmentId);

   }

    @GetMapping
    public PageResponseDto<Department> searchDepartment(
           @Valid @ModelAttribute DepartmentSearchRequestDto request){

        return departmentService.searchDepartment(request);
    }

}
