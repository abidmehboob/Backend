package com.alseyahat.app.feature.deal.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name = "dealBooking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DealBooking implements Serializable  {
	
	   static final long serialVersionUID = -798991492884005022L;
	    @Id
	    @Column(name = "deal_booking_id")
	    String dealBookingId= UUID.randomUUID().toString().replace("-", "");

	    
	    @ManyToOne
	    @JoinColumn(name = "deal_id")
	    Deal deal;
	    
	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    Customer customer;
	    	
	    @Column
	    String bookingStatus;
	    
	    @Column
	    String paymentReceipt;
	   	    
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

