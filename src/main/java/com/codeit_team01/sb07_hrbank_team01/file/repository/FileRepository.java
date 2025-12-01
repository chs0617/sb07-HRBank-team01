package com.codeit_team01.sb07_hrbank_team01.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}
