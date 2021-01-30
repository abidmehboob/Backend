package com.alseyahat.app.feature.customer.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "shipping_address")
public class ShippingAddress implements Serializable {

    static final long serialVersionUID = -798991492884005022L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    Long addressId;

    @Column(nullable = false)
    String address;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

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
