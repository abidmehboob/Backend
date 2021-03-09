package com.alseyahat.app.feature.ride.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking implements Serializable  {
	
	   static final long serialVersionUID = -798991492884005022L;
	    @Id
	    @Column(name = "booking_id")
	    String bookingId= UUID.randomUUID().toString().replace("-", "");

	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    Customer customer;
	    	
	    @Column(nullable = false)
	    String dropOffAddress;

	    @Column(nullable = false)
	    double dropOffLat;

	    @Column(nullable = false)
	    double dropOffLong;

	    @Column(nullable = false)
	    String dropOffTime;
	    
	    @Column
	    String mobileNumber;
	    
	    @Column(nullable = false)
	    String pickUpAddress;
	    
	    @Column(nullable = false)
	    double pickUpLat;
	    
	    @Column(nullable = false)
	    double pickUpLong;
	    
	    @Column
	    String pickUpTime;
	    
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

