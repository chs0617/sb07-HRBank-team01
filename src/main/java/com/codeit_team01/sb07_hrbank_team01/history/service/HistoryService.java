package com.codeit_team01.sb07_hrbank_team01.history.service;

import com.codeit_team01.sb07_hrbank_team01.employee.entity.Employee;
import com.codeit_team01.sb07_hrbank_team01.history.dto.responseDto.HistoryChangeLogDto;
import com.codeit_team01.sb07_hrbank_team01.history.dto.responseDto.HistoryDiffDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface HistoryService {

    // 직원 생성 이력 등록
    void createHistory(Employee employee, String memo, HttpServletRequest request);

    // 직원 수정 이력 등록 : 퇴사 포함
    void updateHistory(Employee beforeEmployee, Employee afterEmployee, String memo, HttpServletRequest request);

    // 직원 삭제 이력 등록
    void deleteHistory(Employee employee, String memo, HttpServletRequest request);

    // 이력 조회
    List<HistoryChangeLogDto> getAllHistories();

    // 이력 상세 조회
    List<HistoryDiffDto> getHistoryDetail(Long historyId);
}
