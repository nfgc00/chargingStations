package com.hb.charging.stations.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.charging.stations.domain.ChargingStation;
import com.hb.charging.stations.service.ChargingStationService;

import io.swagger.annotations.ApiOperation;

/**
 * Charging Station Controller class
 * <p>
 * REST API Controller to perform Charging Stations operations
 * <li>
 * 
 * @author fernando
 *
 */
@RestController
@RequestMapping(value="/chargingStation", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChargingStationController {
	
	@Autowired
	ChargingStationService chargingStationService;
	
	@PostMapping(value="/station")
	@ApiOperation(value="Store a new Charging Station, ID should be provided")
	public ResponseEntity<Optional<ChargingStation>> add(@Valid @RequestBody ChargingStation chargingStation) {
		Optional<ChargingStation> stored = chargingStationService.saveChargingStation(chargingStation);
		if(stored.isPresent()) {
			return new ResponseEntity<Optional<ChargingStation>>(stored, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Optional<ChargingStation>>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value="/station/{id}")
	@ApiOperation(value="Search a Charging Station by ID")
	public ResponseEntity<Optional<ChargingStation>> get(@PathVariable Long id) {
		Optional<ChargingStation> station = chargingStationService.getChargingStation(id);
		if(station.isPresent()) {
			return new ResponseEntity<Optional<ChargingStation>>(station, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<ChargingStation>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/stations")
	@ApiOperation(value="List all Charging Stations")
	public ResponseEntity<List<ChargingStation>> getAll() {
		List<ChargingStation> list = chargingStationService.getAll();
		return new ResponseEntity<List<ChargingStation>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/stations/{postalCode}")
	@ApiOperation(value="Search Charging Stations by Postal Code")
	public ResponseEntity<List<ChargingStation>> getByPostalCode(@PathVariable Integer postalCode) {
		List<ChargingStation> list = chargingStationService.getByPostalCode(postalCode);
		return new ResponseEntity<List<ChargingStation>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/search")
	@ApiOperation(value="Search Charging Stations within a radius")
	public ResponseEntity<List<ChargingStation>> searchByRadius(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, @RequestParam("radius") Integer radius) {
		List<ChargingStation> results = chargingStationService.getByLocationRadius(latitude, longitude, radius);
		return new ResponseEntity<List<ChargingStation>>(results, HttpStatus.OK);
	}
	
	@PutMapping(value="/station")
	@ApiOperation(value="Update a Charging Stattion with the given values")
	public ResponseEntity<Optional<ChargingStation>> update(@Valid @RequestBody ChargingStation chargingStation) {
		Optional<ChargingStation> updated = chargingStationService.updateChargingStation(chargingStation);
		if(updated.isPresent()) {
			return new ResponseEntity<Optional<ChargingStation>>(updated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<ChargingStation>>(HttpStatus.NOT_FOUND);
		}
	}

}
