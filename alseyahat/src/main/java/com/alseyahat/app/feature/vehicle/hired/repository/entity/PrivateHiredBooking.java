package com.alseyahat.app.feature.vehicle.hired.repository.entity;

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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name = "privateHiredBooking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrivateHiredBooking implements Serializable  {

	    static final long serialVersionUID = -798991492884005022L;
		@Id
		@Column
		String privateHiredBookingId = UUID.randomUUID().toString().replace("-", "");
	    
		   
	    @ManyToOne
	    @JoinColumn(name = "private_hired_id")
	    PrivateHired privateHired;
	    
	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    Customer customer;

	    @Column
	    String bookingStatus;
	    
	    @Column
	    String paymentReceipt;
	    
	    @Column
	    String transactionId;
	    
	    @Column
	    String pickLocation;
	    
	    @Column
	    Date startDate;
	    
	    @Column
	    Date endDate;
	   	    
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

