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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "hotel_facility")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelFacility implements Serializable  {
	
	    static final long serialVersionUID = -798991492884005022L;
	   
	    @Id
	    String hotelFacilityId= UUID.randomUUID().toString().replace("-", "");

	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    Hotel hotel;
	    
	    @Column
	    String description;
	    
	    @Column
	    boolean isEnabled = Boolean.TRUE;

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
