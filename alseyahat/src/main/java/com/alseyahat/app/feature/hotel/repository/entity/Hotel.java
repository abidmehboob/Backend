package com.alseyahat.app.feature.hotel.repository.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import com.alseyahat.app.feature.review.repository.entity.Review;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name = "hotel")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {

    @Id
    @Column(nullable = false)
    String hotelId = UUID.randomUUID().toString().replace("-", "");
    
    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column
    String email;

    @Column
    String phone;

    @Column
    String registerFrom;

    @Column
    String logo;

    @Column
    String backgroundImage;

    @Column
    String accountNumber;

    @Column
    String accountSortCode;

    @Column
    boolean isEnabled = true;
    
    @Column
    @Lob
    String termAndCondition;
    
    @Column
    boolean isTermCondition = true;
    
    @Column
    String addressLine;
    
    @Column
    String businessType;
    
    @Column
    String city;

    @Column
    String town;

    @Column
    String postcode;

    @Column
    Double latitude;

    @Column
    Double longitude;
    
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Review.class)
    List<Review> review;

    @Column
    Date dateCreated;

    @Column
    Date lastUpdated;

    @PrePersist
    protected void prePersist() {
        lastUpdated = new Date();
        if (dateCreated == null) {
            dateCreated = new Date();
        }
    }
}
