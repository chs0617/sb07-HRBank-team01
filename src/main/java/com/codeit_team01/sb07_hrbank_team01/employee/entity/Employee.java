package com.codeit_team01.sb07_hrbank_team01.employee.entity;

import com.codeit_team01.sb07_hrbank_team01.base.BaseEntity;
import com.codeit_team01.sb07_hrbank_team01.department.entity.Department;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "job_position", nullable = false, length = 50)
    private String jobPosition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "hire_date", nullable = false)
    private Instant hireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private EmployeeStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private File profile;

    @Column(name = "employee_no", nullable = false, unique = true, length = 50)
    private String employeeNo;

    protected Employee() {}

    public Employee(String name, String email,
            String jobPosition, Department department,
            Instant hireDate, String employeeNo, File profile) {
        this.name = name;
        this.email = email;
        this.jobPosition = jobPosition;
        this.department = department;
        this.hireDate = hireDate;
        this.status = EmployeeStatus.ACTIVE;
        this.employeeNo = employeeNo;
        this.profile = profile;
    }

    public void updateInfo(String name, String email,
                           String jobPosition, Department department,
                           Instant hireDate, File profile) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("이메일이 null이거나 empty면 안됩니다.");
        }
        this.email = email.toLowerCase();
        this.name = name;
        this.jobPosition = jobPosition;
        this.department = department;
        this.hireDate = hireDate;
        this.profile = profile;
    }

    public void changeStatus(EmployeeStatus status) {
        this.status = status;
    }
}
