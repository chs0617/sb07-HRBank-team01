package com.codeit_team01.sb07_hrbank_team01.history.repository;

import com.codeit_team01.sb07_hrbank_team01.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

    // 이력 등록
    public void createHistory(History history);

    // 이력 목록 조회
    public void findAllByHistory(History history);

    // 이력 상세 변경 내용 조회
    public History findHistoryById(Long id);

}
