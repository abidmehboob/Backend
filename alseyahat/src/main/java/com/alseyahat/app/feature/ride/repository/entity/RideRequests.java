package com.alseyahat.app.feature.ride.repository.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "rideRequests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideRequests implements Serializable  {
	
	   static final long serialVersionUID = -798991492884005022L;
	    @Id
	    String rideRequestId= UUID.randomUUID().toString().replace("-", "");

	    @Column
	    String bookingId;
	    
	    @Column
	    String CustomerId;
	    	
	    @Column(nullable = false)
	    String dropOffAddress;

	    @Column(nullable = false)
	    double dropOffLat;

	    @Column(nullable = false)
	    double dropOffLong;

	    @Column(nullable = false)
	    Date dropOffTime;
	    
	    @Column
	    String mobileNumber;
	    
	    @Column(nullable = false)
	    String pickUpAddress;
	    
	    @Column(nullable = false)
	    double pickUpLat;
	    
	    @Column(nullable = false)
	    double pickUpLong;
	    
	    @Column
	    Date pickUpTime;
	    
	    @Column
	    String rideStatus;
	    
	    @Column
	    String routeID;
	    
	    @Column
	    String selectedDay;
	    
	    @Column
	    boolean isEnabled = true;

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