package com.alseyahat.app.feature.hotel.repository.entity;

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
@Table(name = "hotelbooking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelBooking implements Serializable  {
	
	   static final long serialVersionUID = -798991492884005022L;
	    @Id
	    @Column(name = "hotel_booking_id")
	    String hotelBookingId= UUID.randomUUID().toString().replace("-", "");

	    
	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    Hotel hotel;
	    
	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    Customer customer;
	    	
	    @Column(nullable = false)
	    String roomType;
	    
	    @Column(nullable = false)
	    Integer childern=0;
	    
	    @Column(nullable = false)
	    Integer adult=1;
	    
	    @Column(nullable = false)
	    Integer extraMatress=1;
	    
	    @Column
        String pickLocation;
	    
	    @Column
	    String paymentReceipt;
	    
	    @Column
	    String transactionId;
	    
	    @Column
	    String bookingStatus;
	    
	    @Column
	    Date startDate;
	    
	    @Column
	    Date endDate;
	    
	    @Column
	    double totalRent;
	    
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

