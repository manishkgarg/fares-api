package com.fare.statistics.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fare.statistics.domains.ResponseDetails;
import com.fare.statistics.models.FareModel;
import com.fare.statistics.oauth.TokenGenerator;
import com.fare.statistics.repositories.APIStatsRepository;

import reactor.core.publisher.Mono;

/**
 * This service class hits mocked fares API in order to retrieve fare
 * information based on origin & destination codes.
 * 
 * @author Manish
 *
 */
@Service
public class FareServiceImpl implements FareService {

	private static final Logger log = LogManager.getLogger(FareServiceImpl.class);

	private static final String AUTHORIZATION = "Authorization";

	@Value("${spring.application.fareservice.resource}")
	private String resource;

	private WebClient client;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private APIStatsRepository repository;

	public FareServiceImpl() {
		client = WebClient.builder().baseUrl(resource).build();
	}

	public Mono<FareModel> retrieveRates(String origin, String destination) {
		LocalDateTime now = LocalDateTime.now();
		String token = tokenGenerator.generateToken();

		String uriString = UriComponentsBuilder.fromHttpUrl(resource).path("/" + origin).path("/" + destination)
				.toUriString();

		WebClient.ResponseSpec responseSpec = client.get().uri(uriString).accept(MediaType.APPLICATION_STREAM_JSON)
				.header(AUTHORIZATION, "Bearer " + token).retrieve();
		
		LocalDateTime after = LocalDateTime.now();
		Long diff = ChronoUnit.SECONDS.between(now, after);

		return responseSpec.onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new Exception("500 error!")))
				.bodyToMono(FareModel.class)
				.doOnNext(response -> repository
						.save(ResponseDetails.builder().statusCode(200).responseTime(diff.intValue()).build()))
				.doOnError(WebClientResponseException.class, ex -> {
					if (HttpStatus.BAD_REQUEST == ex.getStatusCode()) {
						repository
								.save(ResponseDetails.builder().statusCode(400).responseTime(diff.intValue()).build());
					} else if (HttpStatus.UNAUTHORIZED == ex.getStatusCode()) {
						repository
								.save(ResponseDetails.builder().statusCode(401).responseTime(diff.intValue()).build());
					} else if (HttpStatus.FORBIDDEN == ex.getStatusCode()) {
						repository
								.save(ResponseDetails.builder().statusCode(403).responseTime(diff.intValue()).build());
					} else if (HttpStatus.NOT_FOUND == ex.getStatusCode()) {
						repository
								.save(ResponseDetails.builder().statusCode(404).responseTime(diff.intValue()).build());
					} else if (HttpStatus.INTERNAL_SERVER_ERROR == ex.getStatusCode()) {
						repository
								.save(ResponseDetails.builder().statusCode(500).responseTime(diff.intValue()).build());
					}
				}).onErrorResume(WebClientResponseException.class,
						ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
	}
}
