package com.example.springboot.domain.reply.entity;

import com.example.springboot.domain.reply.entity.enums.ReplyType;
import com.example.springboot.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "reply_content", length = 15)
    private String replyContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "reply_with", nullable = false, columnDefinition = "VARCHAR(20)")
    private ReplyType replyWith;

    @CreatedDate
    @Column(name = "reply_created_at", updatable = false)
    private LocalDateTime replyCreatedAt;
}
