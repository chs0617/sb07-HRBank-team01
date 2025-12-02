package com.codeit_team01.sb07_hrbank_team01.exception.dto;

import com.codeit_team01.sb07_hrbank_team01.exception.CustomException;
import com.codeit_team01.sb07_hrbank_team01.exception.ErrorCode;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        String timestamp,
        int status,
        String message,
        String details
) {
    public static ErrorResponseDto from(CustomException e) {
        return ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(e.getErrorCode().getStatus().value())
                .message(e.getErrorCode().getMessage())
                .details(e.getDetails())
                .build();
    }

    public static ErrorResponseDto of(HttpStatus status, String message, String details) {
        return ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .message(message)
                .details(details)
                .build();
    }
}
