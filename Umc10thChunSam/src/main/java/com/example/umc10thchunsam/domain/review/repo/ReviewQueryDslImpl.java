package com.example.umc10thchunsam.domain.review.repo;

import com.example.umc10thchunsam.domain.member.entity.QMember;
import com.example.umc10thchunsam.domain.review.converter.ReviewProjectMapper;
import com.example.umc10thchunsam.domain.review.dto.ReviewOfferResponse;
import com.example.umc10thchunsam.domain.review.entity.QReview;
import com.example.umc10thchunsam.domain.review.entity.Review;
import com.example.umc10thchunsam.domain.store.entity.QRestourant;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;
    private final ReviewProjectMapper mapper; // 컨버터 패턴을 사용. 정보 가공 해주는 기능 분리

    @Override
    public List<ReviewOfferResponse> searchReviews(Predicate predicate) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QRestourant restourant = QRestourant.restourant;  // 리뷰에 해당하는 레스토랑의 이름을 얻기 위하야

        QMember member = QMember.member; // 리뷰를 작성한 사람의 이름을 얻기 위하야

        QReview review = QReview.review;


        return queryFactory
                .select(mapper.toReviewOfferResponse(restourant, member, review))
                .from(review)
                .join(review.restourant, restourant).fetchJoin() // 연관관계 조인
                .join(review.member, member).fetchJoin() //leftJoin(A, B)는 “A 필드(@ManyToOne, @OneToMany 등)를 기준으로 B 테이블(Q타입)을 조인하겠다
                .where(predicate)
                .fetch();
    }
}
