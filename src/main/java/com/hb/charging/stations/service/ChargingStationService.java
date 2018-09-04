package com.hb.charging.stations.service;

import java.util.List;
import java.util.Optional;

import com.hb.charging.stations.domain.ChargingStation;

/**
 * Charging Station Service interface to perform business operations.
 * 
 * @author fernando
 *
 */
public interface ChargingStationService {
	
	/**
	 * Stores a Charging Station, ID should be provided by the user.
	 * <p>
	 * If the Charging Station already exists returns an empty {@code Optional}.
	 * <br>
	 * To Update a Charging Station use the {@link #updateChargingStation(ChargingStation)} method.
	 * 
	 * @param chargingStation Object with the data of the Charging Station
	 * @return {@code Optional<ChargingStation>}
	 */
	Optional<ChargingStation> saveChargingStation(ChargingStation chargingStation);
	
	/**
	 * Returns a Charging Station identified by the given ID.
	 * <p>
	 * If the Charging Station does not exists returns an empty {@code Optional}.
	 * 
	 * @param id Charging Station identifier
	 * @return {@code Optional<ChargingStation>}
	 */
	Optional<ChargingStation> getChargingStation(Long id);
	
	/**
	 * Returns a {@code List} of all Charging Stations stored in the database.
	 * 
	 * @return {@code List<ChargingStation>}
	 */
	List<ChargingStation> getAll();
	
	/**
	 * Returns a {@code List} of Charging Stations filtered by the given Postal Code.
	 * 
	 * @param postalCode Used to limit the search to a specific Postal Code area
	 * @return {@code List<ChargingStation>}
	 */
	List<ChargingStation> getByPostalCode(Integer postalCode);
	
	/**
	 * Returns a {@code List} of Charging Stations located around the given location within the given radius.
	 * <p>
	 * The search area is calculated subtracting and adding the given radius to the latitude and longitude coordinates.
	 * 
	 * @Note This approach is only for demo purposes. More research should be done on this topic.
	 * 
	 * @param latitude Initial latitude point to search around
	 * @param longitude Initial longitude point to search around
	 * @param radius Radius of the search area
	 * @return {@code List<ChargingStation>}
	 */
	List<ChargingStation> getByLocationRadius(Double latitude, Double longitude, Integer radius);

	/**
	 * Returns a Charging Station with the updated data.
	 * <p>
	 * If the Charging Station does not exists returns an empty {@code Optional}.
	 * <br>
	 * To Store a new Charging Station use the {@link #saveChargingStation(ChargingStation)} method.
	 * 
	 * @param chargingStation Object with the data to be updated
	 * @return {@code Optional<ChargingStation>}
	 */
	Optional<ChargingStation> updateChargingStation(ChargingStation chargingStation);

}
