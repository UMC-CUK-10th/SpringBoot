package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class APIResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private final T result;

    // 성공한 경우
    public static <T> APIResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new APIResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우
    public static <T> APIResponse<T> onFailure(BaseErrorCode code, T result) {
        return new APIResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
