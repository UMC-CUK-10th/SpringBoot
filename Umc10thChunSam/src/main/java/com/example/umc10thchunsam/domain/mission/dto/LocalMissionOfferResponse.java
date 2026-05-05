package com.example.umc10thchunsam.domain.mission.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LocalMissionOfferResponse {

    private Long restaurantId;
    private String restaurantName;
    private Integer givePoint;
    private LocalDate validDate;

    public LocalMissionOfferResponse(Long restaurantId, String restaurantName, Integer givePoint, LocalDate validDate) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.givePoint = givePoint;
        this.validDate = validDate;
    }


}
