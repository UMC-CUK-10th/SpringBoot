package com.example.springboot10th.domain.mission.controller;

import com.example.springboot10th.domain.mission.dto.MissionRequestDTO;
import com.example.springboot10th.domain.mission.dto.MissionResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/missions")
public class MissionController {

        @GetMapping
        public ApiResponse<MissionResponseDTO.MissionListResponse> getMissions(
                        @RequestParam(required = false) String region,
                        @RequestParam(required = false) String status,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size) {

                return null;
        }

        @GetMapping("/me")
        public ApiResponse<MissionResponseDTO.MissionListResponse> getMyMissions() {
                return null;
        }

        @GetMapping("/me/progress")
        public ApiResponse<MissionResponseDTO.MissionProgressResponse> getMyMissionProgress() {

                return null;
        }

        @PatchMapping("/{missionId}")
        public ApiResponse<MissionResponseDTO.MissionStatusResponse> updateMissionStatus(
                        @PathVariable Long missionId,
                        @RequestParam String status) {


                return null;
        }

        @PostMapping("/{missionId}/reviews")
        public ApiResponse<MissionResponseDTO.ReviewResponse> createReview(
                        @PathVariable Long missionId,
                        @RequestBody MissionRequestDTO.ReviewRequest request) {


                return null;
        }

        @PostMapping("/{missionId}/reviews/{reviewId}")
        public ApiResponse<MissionResponseDTO.ReviewResponse> updateReview(
                        @PathVariable Long missionId,
                        @PathVariable Long reviewId,
                        @RequestBody MissionRequestDTO.ReviewEditRequest request) {


                return null;
        }
}
