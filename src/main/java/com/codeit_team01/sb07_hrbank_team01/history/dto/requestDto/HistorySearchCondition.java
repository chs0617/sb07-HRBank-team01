package com.codeit_team01.sb07_hrbank_team01.history.dto.requestDto;

import com.codeit_team01.sb07_hrbank_team01.history.entity.HistoryType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class HistorySearchCondition {
    // 검색 조건
    private String employeeNo;
    private String memo;
    private String ipAddress;
    private Instant startDate;
    private Instant endDate;
    private HistoryType type;

    // 정렬 타입
    private HistorySortType sortType;

    //정렬 타입 정의
    public enum HistorySortType{
        TIME_ASC,
        TIME_DESC,
        IP_ASC,
        IP_DESC
    }

    //커서ID
    private Long cursorId;
    //페이지 크기
    private Integer size;
    //페이지 크기 반환
    public int getPageSize(){
        return (size != null && size > 0) ? size : 10;
    }
    //정렬타입반환
    public HistorySortType getSortTypeOrDefault(){
        return sortType != null ? sortType : HistorySortType.TIME_DESC;
    }
}
