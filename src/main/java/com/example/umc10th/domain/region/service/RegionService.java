package com.example.umc10th.domain.region.service;

import com.example.umc10th.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
}