package com.codeit_team01.sb07_hrbank_team01.employee.service;

import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import com.codeit_team01.sb07_hrbank_team01.employee.repository.EmployeeRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.time.ZoneId;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeBackupService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public void backupEmployeesToCsv(String filePath) {
        try (
                Writer writer = new FileWriter(filePath);
                CSVWriter csvWriter = new CSVWriter(writer);
                Stream<Employee> employeeStream = employeeRepository.streamAll()
        ) {
            csvWriter.writeNext(new String[]{
                    "employeeNo", "name", "email", "department", "jobPosition", "hireDate", "status"
            });

            employeeStream.forEach(employee -> {
                csvWriter.writeNext(new String[]{
                        employee.getEmployeeNo(),
                        employee.getName(),
                        employee.getEmail(),
                        (employee.getDepartment() != null ? employee.getDepartment().getName() : ""),
                        employee.getJobPosition(),
                        (employee.getHireDate() != null
                                ? employee.getHireDate().atZone(ZoneId.systemDefault()).toLocalDate().toString()
                                : ""),
                        (employee.getStatus() != null ? employee.getStatus().name() : "")
                });
            });

        } catch (IOException e) {
            System.err.println("CSV 백업 중 오류 발생: " + e.getMessage());
        }
    }
}
