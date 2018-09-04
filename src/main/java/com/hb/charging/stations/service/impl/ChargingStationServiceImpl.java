package com.hb.charging.stations.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hb.charging.stations.domain.ChargingStation;
import com.hb.charging.stations.repository.ChargingStationRepository;
import com.hb.charging.stations.service.ChargingStationService;

/**
 * Charging Station Service implementation class to perform business operations.
 * <p>
 * Implement Charging Stations Service business operations.
 * 
 * @author fernando
 *
 */
@Service
@Transactional
public class ChargingStationServiceImpl implements ChargingStationService {
	
	@Autowired
	ChargingStationRepository chargingStationRepository;

	@Override
	public Optional<ChargingStation> saveChargingStation(ChargingStation chargingStation) {
		Optional<ChargingStation> current = chargingStationRepository.findById(chargingStation.getId());
		if(current.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(chargingStationRepository.save(chargingStation));
		}
	}
	
	@Override
	public Optional<ChargingStation> getChargingStation(Long id) {
		return chargingStationRepository.findById(id);
	}
	
	@Override
	public List<ChargingStation> getAll() {
		return chargingStationRepository.findAll();
	}
	
	@Override
	public List<ChargingStation> getByPostalCode(Integer postalCode) {
		return chargingStationRepository.findByPostalCode(postalCode);
	}
	
	@Override
	public List<ChargingStation> getByLocationRadius(Double latitude, Double longitude, Integer radius) {
		/*
		 * For simplicity the search area is calculated just subtracting and adding the radius (Integer) 
		 * to the latitude and longitude, this is not the correct approach.
		 */
		Double latMin = latitude - radius;
		Double latMax = latitude + radius;
		Double lonMin = longitude - radius;
		Double lonMax = longitude + radius;
		return chargingStationRepository.searchByLocationRatio(latMin , latMax, lonMin , lonMax );
	}
	
	@Override
	public Optional<ChargingStation> updateChargingStation(ChargingStation chargingStation) {
		Optional<ChargingStation> current = chargingStationRepository.findById(chargingStation.getId());
		if(current.isPresent()) {
			return Optional.of(chargingStationRepository.save(chargingStation));
		} else {
			return Optional.empty();
		}
	}

}
