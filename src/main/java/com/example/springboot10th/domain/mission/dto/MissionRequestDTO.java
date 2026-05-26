package com.example.springboot10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionRequestDTO {

    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional) {
    }
}