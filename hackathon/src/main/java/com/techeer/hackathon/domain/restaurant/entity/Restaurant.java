package com.techeer.hackathon.domain.restaurant.entity;

import com.techeer.hackathon.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="RESTAURANT")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {
    @Id
    private Long id;

    @Column(nullable = false, length = 30)
    private String restaurantName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantCategory category;

    @CreatedDate
    private LocalDateTime createdBy;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Restaurant(
            String restaurantName,
            RestaurantCategory category) {
        this.restaurantName = restaurantName;
        this.category = category;
    }

    public void deleteRestaurant(){
        this.isDeleted = true;
    }
}
