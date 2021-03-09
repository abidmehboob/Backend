package com.alseyahat.app.feature.otp.repository.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "twoFactorAuth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TwoFactorAuth implements Serializable {
	private static final long serialVersionUID = 100L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String userId;
	
	@Column
	private String eventTitle;

	@Column
	private String phoneNumber;
	
	@Column
	private String otpData;
	
	@Column
	private String consumedDate;
	
	@Column
	private Integer loginSessionId;
	
	@Column
	private String status;

	@Column
	private String expiresIn;

	@Column
	private String createdDate;
	
	@Column
    private String modifiedDate;

	
}
