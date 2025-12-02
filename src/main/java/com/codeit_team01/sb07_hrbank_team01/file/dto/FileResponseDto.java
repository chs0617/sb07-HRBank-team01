package com.codeit_team01.sb07_hrbank_team01.file.dto;

import com.codeit_team01.sb07_hrbank_team01.file.entity.File;

public record FileResponseDto(
        Long id,
        String name,
        String type,
        Long size
) {
}
