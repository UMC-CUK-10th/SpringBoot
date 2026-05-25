package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(unique = true)
    private String socialUid;

    protected Member() {
    }

    public Member(
            Long id,
            String email,
            String password,
            String role,
            SocialType socialType,
            String socialUid
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.socialType = socialType;
        this.socialUid = socialUid;
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

    public String getRole() {
        return role;
    }

    public SocialType getSocialType() {
        return socialType;
    }

    public String getSocialUid() {
        return socialUid;
    }
}
