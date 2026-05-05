package com.example.umc10thchunsam.global;

public record PageInfo(
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {}
