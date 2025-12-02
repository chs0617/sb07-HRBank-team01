package com.codeit_team01.sb07_hrbank_team01.employee.repository;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long countByDepartmentId(Long departmentId);
    boolean existsByEmailIgnoreCase(String email);
}