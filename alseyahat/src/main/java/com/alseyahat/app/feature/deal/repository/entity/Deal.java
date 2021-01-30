package com.alseyahat.app.feature.deal.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "deal")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Deal implements Serializable {

  static final long serialVersionUID = -798991492884005022L;
  
  @Id
  String dealId= UUID.randomUUID().toString().replace("-", "");
  
  @Column(nullable = false)
  String name;

  @Column
  String description;
  
  @Column
  boolean isEnabled = Boolean.TRUE;
  
  @OneToMany(mappedBy = "deal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Review.class)
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
