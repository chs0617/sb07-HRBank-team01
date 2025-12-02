package com.codeit_team01.sb07_hrbank_team01.employee.service;

import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import com.codeit_team01.sb07_hrbank_team01.employee.repository.EmployeeRepository;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import com.codeit_team01.sb07_hrbank_team01.file.repository.FileRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.time.ZoneId;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmployeeBackupService {

    private final EmployeeRepository employeeRepository;
    private final FileRepository fileRepository;

    @Transactional
    public File backupEmployeesToCsv(String filePath) {
        long fileSize = 0L;
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

            writer.flush();
            fileSize = Files.size(Paths.get(filePath));

        } catch (IOException e) {
            System.err.println("CSV 백업 중 오류 발생: " + e.getMessage());
            return null;
        }

        File fileEntity = File.builder()
                .name(filePath.substring(filePath.lastIndexOf('/') + 1))
                .type("text/csv")
                .size(fileSize)
                .build();

        fileEntity = fileRepository.save(fileEntity);

        return fileEntity;
    }
}
