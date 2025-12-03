package com.codeit_team01.sb07_hrbank_team01.history.dto.responseDto;

import com.codeit_team01.sb07_hrbank_team01.history.entity.History;
import com.codeit_team01.sb07_hrbank_team01.history.entity.HistoryDetail;

// 직원 정보 수정 이력 변경 내용 (상세 조회용)
public record DiffDto(
        String propertyName,
        String before,
        String after
) {
    public static DiffDto from(HistoryDetail historyDetail) {
        return new  DiffDto(
                historyDetail.getPropertyName(),
                historyDetail.getBeforeValue(),
                historyDetail.getAfterValue()
        );
    }
}
