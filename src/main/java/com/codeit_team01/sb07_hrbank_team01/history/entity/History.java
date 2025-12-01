package com.codeit_team01.sb07_hrbank_team01.history.entity;

import com.codeit_team01.sb07_hrbank_team01.base.BaseEntity;
import com.codeit_team01.sb07_hrbank_team01.empolyee.entity.Employee;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_histories")
public class History extends BaseEntity {

    // 수정 이력 Id

    // 수정 유형
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private HistoryType type;

    // 선택적으로 화면에서 입력
    @Column(length = 255)
    private String memo;

    // ip_address : 서버 자동 추출
    @Column(name = "ip_address", length = 255)
    private String ipAddress;

    // 이력 등록 시간

    // 직원 사번
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;

}
