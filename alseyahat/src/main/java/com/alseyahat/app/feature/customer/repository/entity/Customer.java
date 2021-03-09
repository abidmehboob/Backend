package com.alseyahat.app.feature.customer.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Setter
@Getter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer", indexes = {@Index(name = "UQ_CUSTOMERS_PHONE_IDX", columnList = "phone", unique = true)})
public class Customer implements UserDetails {

    static final long serialVersionUID = -787991492884005033L;

    @Id
    @Column(name = "customer_id")
    String customerId = UUID.randomUUID().toString().replace("-", "");

    String name;

    String email;

    @Column(nullable = false)
    String password;

    @Column
    String deviceType;

    @Column
    String channelId;

    @Column
    String deviceModel;

    @Column(nullable = false)
    String phone;

    @Column
    String deviceId;

    @Column
    String deviceToken;
    
    @Column
    String cnic;
    
    @Column
    String personalKey;

    @Column(nullable = true)
    String securityKey = UUID.randomUUID().toString().replace("-", "");

    @Column(nullable = true)
    String aesKey = UUID.randomUUID().toString().replace("-", "");

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ShippingAddress> shippingAddress;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_type", referencedColumnName = "id")
    CustomerType customerType;
    
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<HotelBooking> hotelBooking;
   

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "branch_id")
//    Branch branch;

    @Column
    Date dateCreated;

    @Column
    Date lastUpdated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return CollectionUtils.emptyCollection();
    	return null;
    }

    @Override
    public String getUsername() {
        return isNotEmpty(personalKey) ? personalKey : personalKey;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    protected void prePersist() {
        lastUpdated = new Date();
        if (dateCreated == null) {
            dateCreated = new Date();
        }
    }
}
