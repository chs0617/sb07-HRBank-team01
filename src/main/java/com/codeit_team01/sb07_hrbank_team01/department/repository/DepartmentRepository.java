package com.codeit_team01.sb07_hrbank_team01.department.repository;

import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
