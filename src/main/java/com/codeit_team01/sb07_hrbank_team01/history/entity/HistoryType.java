package com.codeit_team01.sb07_hrbank_team01.history.entity;

import lombok.Getter;

@Getter
public enum HistoryType {

    // 직원 추가
    EMPLOYEE_CREATE("신규 직원 등록"),

    // 정보 수정
    EMPLOYEE_UPDATE("직원 정보 수정"),

    // 직원 삭제
    EMPLOYEE_DELETE("직원 삭제");

    private final String defaultMemo;

    HistoryType(String defaultMemo) {
        this.defaultMemo = defaultMemo;
    }
}
