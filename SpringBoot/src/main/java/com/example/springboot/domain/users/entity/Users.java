package com.example.springboot.domain.users.entity;

import com.example.springboot.domain.users.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "nickname", nullable = false, length = 15)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private UserStatus userStatus;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @Column(name = "user_phone_number", length = 15)
    private String userPhoneNumber;

    @Column(name = "email", length = 15)
    private String email;

    @Column(name = "user_point", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long userPoint;

    @Column(name = "user_password", nullable = false, length = 15)
    private String userPassword;

    @CreatedDate
    @Column(name = "user_created_at", updatable = false)
    private LocalDateTime userCreatedAt;

    // 연관관계
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserFood> userFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @Builder.Default
    private List<com.example.springboot.domain.review.entity.Review> reviewList = new ArrayList<>();
}
