package com.fare.statistics.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fare.statistics.models.FareModel;
import com.fare.statistics.models.Page;
import com.fare.statistics.models.Root;
import com.fare.statistics.services.AirportServiceImpl;
import com.fare.statistics.services.FareServiceImpl;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FareController.class)
public class FareControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FareServiceImpl fareService;

	@MockBean
	private AirportServiceImpl airportService;

	@Test
	public void getFareInformationBasedOnInputLocations() throws Exception {
		when(fareService.retrieveRates("yow", "bba")).thenReturn(
				Mono.just(FareModel.builder().amount(100.00).currency("EUR").origin("yow").destination("bba").build()));
		this.mockMvc.perform(get("/fare-price").param("origin", "yow").param("destination", "bba"))
				.andExpect(status().isOk());
	}

	@Test
	public void getAirportInformationBasedOnInputLocationCode() throws Exception {
		when(airportService.retrieveRoutes())
				.thenReturn(Mono.just(Root.builder().page(Page.builder().size(250).build()).build()));
		this.mockMvc.perform(get("/fare-airports").param("code", "yow")).andExpect(status().isOk());
	}

}