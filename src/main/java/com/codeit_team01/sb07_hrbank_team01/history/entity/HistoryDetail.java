package com.codeit_team01.sb07_hrbank_team01.history.entity;

import com.codeit_team01.sb07_hrbank_team01.base.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "history_details")
public class HistoryDetail extends BaseEntity {

    // uuid

    // 변경 상세 내용
    @Column(name = "property_name", length = 100, nullable = false)
    private String propertyName;

    // 변경 전
    @Column(name = "before_value", length = 255)
   private String beforeValue;

    // 변경 후
    @Lob
    @Column(name = "after_value", length = 255)
    private String afterValue;

    // 생성 시간

    // history_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id", nullable = false)
    private History history;
}
