package com.alseyahat.app.feature.employee.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Setter
@Getter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "employees", indexes = {@Index(name = "UQ_EMPLOYEES_PHONE_IDX", columnList = "phone", unique = true),
        @Index(name = "UQ_EMPLOYEES_EMAIL_IDX", columnList = "email", unique = true)})
public class Employee implements UserDetails {

    static final long serialVersionUID = -787991492884005033L;

    @Id
    @Column(name = "employee_id")
    String employeeId = UUID.randomUUID().toString().replace("-", "");

    @Column(nullable = false)
    String name;

    String email;

    @Column(nullable = false)
    String password;

//    @ManyToMany(mappedBy = "employee")
//    List<EmployeeRole> roles = new ArrayList<>();

//    @ManyToMany(mappedBy = "employees")
//    List<Branch> branches = new ArrayList<>();

    @Column
    String hotelId;
    
    @Column
    String phone;

    @Column
    boolean isEnabled = Boolean.TRUE;

    @Column(nullable = true)
    String securityKey = UUID.randomUUID().toString().replace("-", "");

    @Column(nullable = true)
    String aesKey = UUID.randomUUID().toString().replace("-", "");
    
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee_role", referencedColumnName = "role_id")
    @Column(nullable = false)
    String roleId;

    @Column
    Date dateCreated;

    @Column
    Date lastUpdated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;

    }

    @Override
    public String getUsername() {
        return isNotEmpty(getEmail()) ? getEmail() : getPhone();
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
        return isEnabled;
    }

    @PrePersist
    protected void prePersist() {
        lastUpdated = new Date();
        if (dateCreated == null) {
            dateCreated = new Date();
        }
    }
}
