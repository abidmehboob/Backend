package com.alseyahat.app.feature.vehicle.hired.repository.entity;

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
@Table(name = "privateHired")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrivateHired implements Serializable  {

	   static final long serialVersionUID = -798991492884005022L;
		@Id
		String privateHiredId = UUID.randomUUID().toString().replace("-", "");
	    
	    @Column(nullable = false)
	    String name;

	    @Column
	    String description;
	    
	    @Column
	    String images;

	    @Column
	    String type;
	    
	    @Column
	    String city;
	    
	    @Column
	    String district;
	    
	    @Column
	    Double latitude;

	    @Column
	    Double longitude;

	    @Column
	    String numberOfSeat;

	    @Column
	    boolean isEnabled = true;
    
	    @Column
	   	boolean driverRequired = false;
	    
	    @Column
		boolean fuel = false;
	    
	    @Column
		double perDayRate;
	    
	    @Column
	    Double privateHiredAverageRating=3.0;
	    
	    @Column
	    boolean hotPrivateHired =  Boolean.FALSE;

	    @Column
	    boolean newArrival =  Boolean.FALSE;

	    @Column
	    boolean advertised = Boolean.FALSE;
	    
	    @OneToMany(mappedBy = "privateHired", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Review.class)
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
