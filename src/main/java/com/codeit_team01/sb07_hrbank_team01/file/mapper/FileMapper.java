package com.codeit_team01.sb07_hrbank_team01.file.mapper;

import com.codeit_team01.sb07_hrbank_team01.file.dto.FileResponseDto;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public FileResponseDto toDto(File file) {
        return new FileResponseDto(
                file.getId(),
                file.getName(),
                file.getType(),
                file.getSize()
        );
    }
}
