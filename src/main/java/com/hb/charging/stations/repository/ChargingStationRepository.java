package com.hb.charging.stations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hb.charging.stations.domain.ChargingStation;

/**
 * Charging Station Repository interface to perform database operations
 * 
 * @author fernando
 *
 */
@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

	/**
	 * Returns a {@code List} of Charging Stations filtered by Postal Code.
	 * 
	 * @param postalCode to filter the results.
	 * @return {@code List<ChargingStation>}
	 */
	List<ChargingStation> findByPostalCode(Integer postalCode);

	/**
	 * Returns a {@code List} of Charging Stations located in a specific area limited by the minimum and maximum latitude and longitude.
	 * 
	 * @param latMin Minimum latitude
	 * @param latMax Maximum latitude
	 * @param lonMin Minimum longitude
	 * @param lonMax Maximal longitude
	 * @return {@code List<ChargingStation>}
	 */
	@Query("SELECT cs FROM ChargingStation cs WHERE cs.latitude BETWEEN :latMin AND :latMax AND cs.longitude BETWEEN :lonMin AND :lonMax")
	List<ChargingStation> searchByLocationRatio(@Param("latMin") Double latMin, @Param("latMax") Double latMax,
			@Param("lonMin") Double lonMin, @Param("lonMax") Double lonMax);

}
