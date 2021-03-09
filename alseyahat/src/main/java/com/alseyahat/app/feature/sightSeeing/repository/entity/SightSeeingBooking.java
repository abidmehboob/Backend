package com.alseyahat.app.feature.sightSeeing.repository.entity;

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
@Table(name = "sightSeeingBooking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SightSeeingBooking implements Serializable  {

	    static final long serialVersionUID = -798991492884005022L;
	    @Id
	    @Column(name = "sightSeeing_booking_id")
	    String sightSeeingBookingId= UUID.randomUUID().toString().replace("-", "");
	    
	    @ManyToOne
	    @JoinColumn(name = "sight_seeing_id")
	    SightSeeing sightSeeing;
	    
	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    Customer customer;
	    
	    @Column(nullable = false)
	    Date bookedDate;

	    @Column
	    String bookingStatus;
	    
	    @Column
	    double totalFare;
	    
	    @Column
	    boolean isEnabled = true;
	    
	    @Column
	    Date dateCreated;

	    @Column
	    Date lastUpdated;
	    
	    @Column
	    String pickLocation;
	    
	    @Column
	    String paymentReceipt;
	    
	    @Column
	    String transactionId;
	    
	    @Column
	    Double sightAverageRating=3.0;
	    
	    @Column
	    boolean hotSight =  Boolean.FALSE;

	    @Column
	    boolean newArrival =  Boolean.FALSE;

	    @Column
	    boolean advertised = Boolean.FALSE;
	
	    @PrePersist
	    protected void prePersist() {
	        lastUpdated = new Date();
	        if (dateCreated == null) {
	            dateCreated = new Date();
	        }
	    }
}
