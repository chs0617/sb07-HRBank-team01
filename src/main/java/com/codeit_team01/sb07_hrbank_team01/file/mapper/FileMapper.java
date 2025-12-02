package com.codeit_team01.sb07_hrbank_team01.file.mapper;

import com.codeit_team01.sb07_hrbank_team01.file.dto.FileResponseDto;
import com.codeit_team01.sb07_hrbank_team01.file.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileResponseDto toDto(File file);
}
