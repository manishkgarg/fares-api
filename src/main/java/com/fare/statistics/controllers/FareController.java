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
import com.fare.statistics.services.StatisticsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequiredArgsConstructor
public class FareController {

	private static final String FARE_STATISTICS = "/fare-statistics";

	private static final String FARE_AIRPORTS = "/fare-airports";

	private static final String FARE_PRICE = "/fare-price";

	private static final Logger log = LogManager.getLogger(FareController.class);

	private final FareService fareService;

	private final AirportService airportService;

	private final StatisticsService statisticsService;

	/**
	 * This particular method will provide the fare information based on input
	 * location.
	 * 
	 * @param request
	 * @return fare
	 */
	@GetMapping(FARE_PRICE)
	public Mono<FareModel> fetchRates(@Valid @ModelAttribute Request request) {
		UUID uuid = UUID.randomUUID();
		log.info("unique id generated is: " + uuid + " for origin: " + request.getOrigin() + " & destination: "
				+ request.getDestination());
		return fareService.retrieveRates(request.getOrigin(), request.getDestination());
	}

	/**
	 * This method is to provide API statistics.
	 * 
	 * @return count(Total number of requests processed), count(OK response
	 *         status code), count(4xx response), count(5xx response),
	 *         min(ResponseTime), max(ResponseTime) & average(ResponseTime).
	 */
	@GetMapping(FARE_STATISTICS)
	public ResponseEntity<String> provideApiStats() {
		String result = statisticsService.getApiStats();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * This particular method will provide the airport information json in
	 * details
	 * 
	 * @return location json
	 */
	@GetMapping(FARE_AIRPORTS)
	public Mono<Root> getAirportDetails() {
		UUID uuid = UUID.randomUUID();
		log.info("Unique id generated for fetching airport details: {} " + uuid);
		return airportService.retrieveRoutes();
	}

}
