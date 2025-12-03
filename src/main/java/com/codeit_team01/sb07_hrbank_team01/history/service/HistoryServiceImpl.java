package com.codeit_team01.sb07_hrbank_team01.history.service;

import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import com.codeit_team01.sb07_hrbank_team01.history.entity.History;
import com.codeit_team01.sb07_hrbank_team01.history.entity.HistoryType;
import com.codeit_team01.sb07_hrbank_team01.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService{
    private final HistoryRepository historyRepository;

    // 직원 생성 이력 등록
    @Override
    public void createHistory(Employee employee, String memo, String ipAddress) {
        History history = History.createHistory(HistoryType.EMPLOYEE_CREATE, employee, memo, ipAddress);

        // 전체 필드 추가
        history.addDetail("입사일", null, employee.getHireDate().toString());
        history.addDetail("이름", null, employee.getName());
        history.addDetail("직함", null, employee.getJobPosition());
        history.addDetail("부서명", null, employee.getDepartment().toString());
        history.addDetail("이메일", null, employee.getEmail());
        history.addDetail("사번", null, employee.getEmployeeNo());
        history.addDetail("상태", null, employee.getStatus().toString());

        historyRepository.save(history);
    }

    // 직원 수정 이력 등록
    @Override
    public void updateHistory(Employee beforeEmployee, Employee afterEmployee, String memo, String ipAddress) {
        History history = History.createHistory(HistoryType.EMPLOYEE_UPDATE, afterEmployee, memo, ipAddress);

        //입사일 수정
        if(!Objects.equals(beforeEmployee.getHireDate(), afterEmployee.getHireDate())){
            history.addDetail("입사일", beforeEmployee.getHireDate().toString(), afterEmployee.getHireDate().toString());
        }
        //이름 수정
        if(!Objects.equals(beforeEmployee.getName(), afterEmployee.getName())){
            history.addDetail("이름",  beforeEmployee.getName(), afterEmployee.getName());
        }
        //직함 수정
        if(!Objects.equals(beforeEmployee.getJobPosition(), afterEmployee.getJobPosition())){
            history.addDetail("직함", beforeEmployee.getJobPosition(), afterEmployee.getJobPosition());
        }
        //부서명 수정
        if(!Objects.equals(beforeEmployee.getDepartment(), afterEmployee.getDepartment())){
            history.addDetail("부서", beforeEmployee.getDepartment().toString(), afterEmployee.getDepartment().toString());
        }
        //이메일 수정
        if(!Objects.equals(beforeEmployee.getEmail(), afterEmployee.getEmail())){
            history.addDetail("이메일", beforeEmployee.getEmail(), afterEmployee.getEmail());
        }
        //상태 변경 체크
        if(!Objects.equals(beforeEmployee.getStatus(), afterEmployee.getStatus())){
            history.addDetail("상태", beforeEmployee.getStatus().toString(), afterEmployee.getStatus().toString());
        }
        historyRepository.save(history);
    }

    // 직원 삭제 이력 등록
    @Override
    public void deleteHistory(Employee employee, String memo, String ipAddress) {
        History history = History.createHistory(HistoryType.EMPLOYEE_DELETE, employee, memo, ipAddress);

        // 전체 필드 추가
        history.addDetail("입사일", employee.getHireDate().toString(), null);
        history.addDetail("이름", employee.getName(), null);
        history.addDetail("직함", employee.getJobPosition(), null);
        history.addDetail("부서명", employee.getDepartment().toString(), null);
        history.addDetail("이메일", employee.getEmail(), null);
        history.addDetail("사번", employee.getEmployeeNo(), null);
        history.addDetail("상태", employee.getStatus().toString(), null);

        historyRepository.save(history);
    }

    // 전체 조회
    @Override
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

}
