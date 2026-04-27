package com.example.umc10thchunsam.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;

public class MemberMissionReqDto {


    @Getter
    @Builder
    public static class ReqbyId{
        private Long id;
    }


}
