package com.codeit_team01.sb07_hrbank_team01.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<HistoryRepository, UUID> {

}
