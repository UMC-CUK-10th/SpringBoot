package com.example.umc10thchunsam.domain.member.exception;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thchunsam.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }

}
//실수로 로컬에서 브랜치 새로 안파고 기존 브랜치에 push했더니 PR을 생성할 수 가 없네여
//그래서 로컬에서 새로 브랜치 파고 다시 올려요. 새롭게 추가 된 코드들은 기존 4주차 2번쨰 commit에서 확인 할 수 있습니다
//5주차 pr에서 찾아봐도 되는데 번거로와서 ㅋ;;