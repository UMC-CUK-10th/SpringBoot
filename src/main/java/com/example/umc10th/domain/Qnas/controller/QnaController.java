package com.example.umc10th.domain.Qnas.controller;

import com.example.umc10th.domain.Qnas.dto.QnaReqDTO;
import com.example.umc10th.domain.Qnas.dto.QnaResDTO;
import com.example.umc10th.domain.Qnas.exception.code.QnaSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qnas")
public class QnaController {

    // 문의 작성
    @PostMapping("")
    public ApiResponse<String> createQna(@RequestBody QnaReqDTO.CreateDTO request) {
        return ApiResponse.onSuccess(QnaSuccessCode.QNA_CREATE_OK, "문의 등록에 성공하였습니다.");
    }

    // 나의 문의 내역 조회
    @GetMapping("/list")
    public ApiResponse<QnaResDTO.QnaListDTO> getMyQnas() {
        return ApiResponse.onSuccess(QnaSuccessCode.QNA_LIST_OK, null);
    }
}
