package com.example.umc10thchunsam.domain.member.entity.mapping;

import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.member.entity.Promise;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_promise")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPromise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_promise_id")        // ERD의 '유저약관ID promiseId' (PK)
    private Long id;      // 자바 필드명만 구분

    @Column(name = "agree", nullable = false)
    private Boolean agree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="promise_id")// ERD의 약관ID(FK)
    private Promise promise;        // FK to promise.promiseId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")       // ERD의 id(user_id)
    private Member member;              // FK to member.id
}
