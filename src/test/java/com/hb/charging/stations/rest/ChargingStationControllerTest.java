/**
 * 
 */
package com.hb.charging.stations.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.charging.stations.domain.ChargingStation;
import com.hb.charging.stations.service.ChargingStationService;

/**
 * Charging Station Controller Test class
 * 
 * @author fgon
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ChargingStationController.class)
public class ChargingStationControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ChargingStationService chargingStationService;
	
	@Autowired
	ObjectMapper objectMapper;
	
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
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#add(com.hb.charging.stations.domain.ChargingStation)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testAdd() throws JsonProcessingException, Exception {
		when(chargingStationService.saveChargingStation(any())).thenReturn(Optional.of(station));
		mockMvc.perform(post("/chargingStation/station")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(station)))
			.andDo(print()).andExpect(status().isCreated());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#get(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testGet() throws Exception {
		when(chargingStationService.getChargingStation(any())).thenReturn(Optional.of(station));
		mockMvc.perform(get("/chargingStation/station/1000")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#getAll()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetAll() throws Exception {
		when(chargingStationService.getAll()).thenReturn(Arrays.asList(station));
		mockMvc.perform(get("/chargingStation/stations")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#getByPostalCode(java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetByPostalCode() throws Exception {
		when(chargingStationService.getByPostalCode(any())).thenReturn(Arrays.asList(station));
		mockMvc.perform(get("/chargingStation/stations/10117")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#searchByRadius(java.lang.Double, java.lang.Double, java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testSearchByRadius() throws Exception {
		when(chargingStationService.getByLocationRadius(any(), any(), any())).thenReturn(Arrays.asList(station));
		mockMvc.perform(get("/chargingStation/search")
				.contentType(MediaType.APPLICATION_JSON)
				.param("latitude", "52.516382")
				.param("longitude", "15.378306")
				.param("radius", "1"))
			.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link com.hb.charging.stations.rest.ChargingStationController#update(com.hb.charging.stations.domain.ChargingStation)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		when(chargingStationService.updateChargingStation(any())).thenReturn(Optional.of(station));
		mockMvc.perform(put("/chargingStation/station")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(station)))
			.andDo(print()).andExpect(status().isOk());
	}

}
