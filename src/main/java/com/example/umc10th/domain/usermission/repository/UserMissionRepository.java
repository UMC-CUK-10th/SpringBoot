package com.example.umc10th.domain.usermission.repository;

import com.example.umc10th.domain.usermission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findByUserId(Long userId, Pageable pageable);
}