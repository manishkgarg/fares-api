package com.fare.statistics.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.fare.statistics.models.FareModel;
import com.fare.statistics.models.Request;
import com.fare.statistics.models.Root;
import com.fare.statistics.services.AirportService;
import com.fare.statistics.services.FareService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequiredArgsConstructor
public class FareController {

	private static final Logger log = LogManager.getLogger(FareController.class);

	private final FareService fareService;

	private final AirportService airportService;

	/**
	 * This particular method will provide the fare information based on input
	 * location.
	 * 
	 * @param request
	 * @return fare
	 */
	@GetMapping("/fare-price")
	public Mono<FareModel> fetchRates(@Valid @ModelAttribute Request request) {
		UUID uuid = UUID.randomUUID();
		log.info("unique id generated is: " + uuid + " for origin: " + request.getOrigin() + " & destination: "
				+ request.getDestination());
		return fareService.retrieveRates(request.getOrigin(), request.getDestination());
	}

	/**
	 * This method is to provide below statistics for given API.
	 * 
	 * @param API
	 * @return count(Total number of requests processed), count(OK response
	 *         status code), count(4xx response), count(5xx response),
	 *         min(ResponseTime), max(ResponseTime) & average(ResponseTime).
	 */
	@GetMapping("/fare-statistics")
	public ResponseEntity<String> provideApiStats(String apiName) {
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * This particular method will provide the airport information json in
	 * details
	 * 
	 * @return location json
	 */
	@GetMapping("/fare-airports")
	public Mono<Root> getAirportDetails() {
		UUID uuid = UUID.randomUUID();
		log.info("unique id generated for fetching airport details : " + uuid);
		return airportService.retrieveRoutes();
	}

}
