package com.codeit_team01.sb07_hrbank_team01.backup.mapper;

import com.codeit_team01.sb07_hrbank_team01.backup.dto.response.BackupResponseDto;
import com.codeit_team01.sb07_hrbank_team01.backup.entity.Backup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BackupMapper {
  BackupResponseDto toBackupResponseDto(Backup backup);
}
