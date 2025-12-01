package com.codeit_team01.sb07_hrbank_team01.file.dto;

public record FileCreateRequestDto(
        byte[] bytes,
        String name,
        String type
) {
}
