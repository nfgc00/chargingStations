package com.hb.charging.stations.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Charging Station Entity class, stores the Charging Station related data, ID should be provided by the user.
 * 
 * @author fernando
 *
 */
@Entity
@Table(name="charging_station")
public class ChargingStation implements Serializable {

	private static final long serialVersionUID = 7618689169701797818L;

	@Id
	@Column(name="charging_station_id", nullable=false)
	@NotNull(message="Charging Station ID must not be null")
	private Long id;
	
	@Column(name="latitude", precision=8, scale=6, nullable=false)
	@NotNull(message="Latitude must not be null")
	private Double latitude;
	
	@Column(name="longitude", precision=9, scale=6, nullable=false)
	@NotNull(message="Longitude must not be null")
	private Double longitude;
	
	@Column(name="postal_code", nullable=false)
	@NotNull(message="Postal Code must not be null")
	private Integer postalCode;

	public ChargingStation() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	
}
