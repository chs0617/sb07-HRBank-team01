package com.codeit_team01.sb07_hrbank_team01.department.controller;

import com.codeit_team01.sb07_hrbank_team01.common.api.ApiResponseDto;
import com.codeit_team01.sb07_hrbank_team01.common.dto.response.PageResponseDto;
import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentCreateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentSearchRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.request.DepartmentUpdateRequestDto;
import com.codeit_team01.sb07_hrbank_team01.department.response.DepartmentResponseDto;
import com.codeit_team01.sb07_hrbank_team01.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;



    @PostMapping
    public ApiResponseDto<DepartmentResponseDto> createDepartment(DepartmentCreateRequestDto request){

        DepartmentResponseDto department = departmentService.createDepartment(request);

        return ApiResponseDto.success(department);
    }

    @GetMapping(value = "/{departmentId}")
    public ApiResponseDto<DepartmentResponseDto> getDepartment(@PathVariable Long departmentId){

        DepartmentResponseDto department = departmentService.getDepartment(departmentId);

        return ApiResponseDto.success(department);

    }

    @PatchMapping(value = "/{departmentId}")
    public ApiResponseDto<DepartmentResponseDto> updateDepartment(@PathVariable Long departmentId, DepartmentUpdateRequestDto request){

        DepartmentResponseDto department = departmentService.updateDepartment(departmentId, request);

        return ApiResponseDto.success(department);
    }

   @DeleteMapping(value = "/{departmentId}")
   public ApiResponseDto<Void> deleteDepartment(@PathVariable Long departmentId){
         departmentService.deleteDepartment(departmentId);

     return ApiResponseDto.success("삭제성공");
   }

    @GetMapping
    public ApiResponseDto<PageResponseDto<Department>> searchDepartment(DepartmentSearchRequestDto request){

        PageResponseDto<Department> departmentPageResponseDto = departmentService.searchDepartment(request);

        return ApiResponseDto.success(departmentPageResponseDto);
    }

}
