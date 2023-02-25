package com.techeer.hackathon.domain.review.entity;

import com.techeer.hackathon.domain.restaurant.entity.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="REVIEW")
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @Column(name="review_id")
    private Long id;

    @Column(nullable = false)
    private String reviewName;

    @Column(nullable = false)
    private String reviewContent;

    // 각각의 레스토랑을 가리키게 함
    // fetch: 읽어오기 전략, eager: 바로 읽어오는것. Lazy는 필요할 때 읽어오는 것
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Builder
    private Review(
            String reviewName,
            String reviewContent,
            Restaurant restaurant) {
        this.reviewName = reviewName;
        this.reviewContent = reviewContent;
        this.restaurant = restaurant;
    }
}

// entity -> repository -> controller -> 필요한 기능은 service에 만들기