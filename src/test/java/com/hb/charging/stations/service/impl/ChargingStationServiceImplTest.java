/**
 * 
 */
package com.hb.charging.stations.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hb.charging.stations.domain.ChargingStation;
import com.hb.charging.stations.repository.ChargingStationRepository;
import com.hb.charging.stations.service.ChargingStationService;

/**
 * Charging Station Service implementation test class
 * 
 * @author fgon
 *
 */
@RunWith(SpringRunner.class)
public class ChargingStationServiceImplTest {
	
	@TestConfiguration
	static class ChargingStationServiceImplTestContextConfiguration {
		
		@Bean
		public ChargingStationService chargingStationService() {
			return new ChargingStationServiceImpl();
		}
	}
	
	@Autowired
	ChargingStationService chargingStationService;
	
	@MockBean
	ChargingStationRepository chargingStationRepository;
	
	ChargingStation station = new ChargingStation();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		station.setId(1000L);
		station.setLatitude(52.516382);
		station.setLongitude(13.378306);
		station.setPostalCode(10117);
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#saveChargingStation(com.hb.charging.stations.domain.ChargingStation)}.
	 */
	@Test
	public void testSaveChargingStation() {
		when(chargingStationRepository.findById(any())).thenReturn(Optional.empty());
		when(chargingStationRepository.save(any())).thenReturn(station);
		ChargingStation chargingStation = new ChargingStation();
		chargingStation.setId(1000L);
		Optional<ChargingStation> saved = chargingStationService.saveChargingStation(chargingStation);
		assertNotNull(saved);
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#getChargingStation(java.lang.Long)}.
	 */
	@Test
	public void testGetChargingStation() {
		when(chargingStationRepository.findById(any())).thenReturn(Optional.of(station));
		Long id = 1000L;
		Optional<ChargingStation> chargingStation = chargingStationService.getChargingStation(id);
		assertEquals(id, chargingStation.get().getId());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#getAll()}.
	 */
	@Test
	public void testGetAll() {
		when(chargingStationRepository.findAll()).thenReturn(Arrays.asList(station));
		List<ChargingStation> result = chargingStationService.getAll();
		assertTrue(result.size() > 0);
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#getByPostalCode(java.lang.Integer)}.
	 */
	@Test
	public void testGetByPostalCode() {
		when(chargingStationRepository.findByPostalCode(any())).thenReturn(Arrays.asList(station));
		List<ChargingStation> resutl = chargingStationService.getByPostalCode(10117);
		assertTrue(resutl.size() > 0);
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#getByLocationRadius(java.lang.Double, java.lang.Double, java.lang.Integer)}.
	 */
	@Test
	public void testGetByLocationRadius() {
		when(chargingStationRepository.searchByLocationRatio(any(), any(), any(), any())).thenReturn(Arrays.asList(station));
		List<ChargingStation> result = chargingStationService.getByLocationRadius(52.516382, 13.378306, 1);
		assertTrue(result.size() > 0);
	}

	/**
	 * Test method for {@link com.hb.charging.stations.service.impl.ChargingStationServiceImpl#updateChargingStation(com.hb.charging.stations.domain.ChargingStation)}.
	 */
	@Test
	public void testUpdateChargingStation() {
		when(chargingStationRepository.findById(any())).thenReturn(Optional.of(station));
		Integer newPostalCode = 10120;
		ChargingStation stationToUpdate = new ChargingStation();
		stationToUpdate.setId(1000L);
		stationToUpdate.setLatitude(52.516382);
		stationToUpdate.setLongitude(13.378306);
		stationToUpdate.setPostalCode(newPostalCode);
		when(chargingStationRepository.save(any())).thenReturn(stationToUpdate);
		Optional<ChargingStation> updatedChargingStation = chargingStationService.updateChargingStation(stationToUpdate);
		assertEquals(newPostalCode, updatedChargingStation.get().getPostalCode());
	}

}
