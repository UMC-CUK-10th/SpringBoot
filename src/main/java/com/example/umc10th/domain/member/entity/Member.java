package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 로그인/JWT 발급용 이메일
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    // BCrypt로 암호화된 비밀번호 저장
    @Column(nullable = false, length = 255)
    private String password;

    // ERD의 name을 닉네임으로 사용
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @Column(nullable = false)
    private Integer point = 0;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Member() {
    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.point = 0;
    }

    public Member(
            String email,
            String password,
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.detailAddress = detailAddress;
        this.point = 0;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.point == null) {
            this.point = 0;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Address getAddress() {
        return address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public Integer getPoint() {
        return point;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}