package com.example.springboot10th.domain.user.controller;

import com.example.springboot10th.domain.mission.entity.UserMission;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/mypage")
    public ResponseEntity<String> getMyPage(@PathVariable("userId") Long userId) {
        User user = userService.getMyPage(userId);
        return ResponseEntity.ok("마이페이지 조회 성공 (닉네임: " + user.getNickname() + ")");
    }

    @GetMapping("/{userId}/missions")
    public ResponseEntity<String> getMyMissions(
            @PathVariable("userId") Long userId,
            @RequestParam("status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        Page<UserMission> missions = userService.getMyMissions(userId, status, page);
        return ResponseEntity
                .ok("내 미션 조회 성공 (총 페이지: " + missions.getTotalPages() + ", 현재 페이지: " + missions.getNumber() + ")");
    }
}
