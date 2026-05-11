package com.example.umc10th.domain.qnas.entity.mapping;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "qnaTypes")
public class QnaTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaTypeId;

    @Column(nullable = false, length = 50)
    private String type;
}