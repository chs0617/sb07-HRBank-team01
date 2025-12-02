package com.codeit_team01.sb07_hrbank_team01.department.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record DepartmentPageResponseDto<T>(
        List<T> content,
        String nextCursor,
        Long nextIdAfter,
        int size,
        long totalElements,
        boolean hasNext
) {
    public static <T> DepartmentPageResponseDto<T> of(Page<T> page, String nextCursor, Long nextIdAfter) {
        return new DepartmentPageResponseDto<>(
                page.getContent(),
                nextCursor,
                nextIdAfter,
                page.getSize(),
                page.getTotalElements(),
                page.hasNext()
        );
    }
}