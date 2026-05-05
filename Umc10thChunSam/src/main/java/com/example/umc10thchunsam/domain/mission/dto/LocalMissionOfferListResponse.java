package com.example.umc10thchunsam.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LocalMissionOfferListResponse {
    private List<LocalMissionOfferResponse> list;
}
