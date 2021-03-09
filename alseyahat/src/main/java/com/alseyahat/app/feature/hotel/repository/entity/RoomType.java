package com.alseyahat.app.feature.hotel.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@Entity
@Table(name = "roomType")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomType implements Serializable {

	static final long serialVersionUID = -798991492884005022L;

	@Id
	@Column(name = "room_type_id", nullable = false)
	String roomTypeId = UUID.randomUUID().toString().replace("-", "");

	@Column(nullable = false)
	String name;

	@Column
	String description;

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
