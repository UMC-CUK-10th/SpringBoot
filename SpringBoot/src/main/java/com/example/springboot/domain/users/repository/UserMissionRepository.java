package com.example.springboot.domain.users.repository;

import com.example.springboot.domain.users.entity.UserMission;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("SELECT um FROM UserMission um WHERE um.users = :user AND um.userMissionStatus = :status")
    Page<UserMission> findByUsersAndUserMissionStatus(@Param("user") Users user, @Param("status") UserMissionStatus status, Pageable pageable);
}
