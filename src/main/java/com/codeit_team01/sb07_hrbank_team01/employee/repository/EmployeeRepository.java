package com.codeit_team01.sb07_hrbank_team01.employee.repository;

import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}