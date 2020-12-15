package com.fare.statistics.services;

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

import com.fare.statistics.models.Root;
import com.fare.statistics.oauth.TokenGenerator;

import reactor.core.publisher.Mono;

/**
 * This service class hits mocked airports API in order to retrieve airport
 * information in json format.
 * 
 * @author Manish
 *
 */
@Service
public class AirportServiceImpl implements AirportService {

	private static final Logger log = LogManager.getLogger(AirportServiceImpl.class);

	private static final String AUTHORIZATION = "Authorization";

	@Value("${spring.application.airportservice.resource}")
	private String resource;

	private WebClient client;

	@Autowired
	private TokenGenerator tokenGenerator;

	public AirportServiceImpl() {
		client = WebClient.builder().baseUrl(resource).build();
	}

	public Mono<Root> retrieveRoutes() {
		String token = tokenGenerator.generateToken();
		String uriString = UriComponentsBuilder.fromHttpUrl(resource).toUriString();
		WebClient.ResponseSpec responseSpec = client.get().uri(uriString).accept(MediaType.APPLICATION_STREAM_JSON)
				.header(AUTHORIZATION, "Bearer " + token).retrieve();
		return responseSpec.onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new Exception("500 error!")))
				.bodyToMono(Root.class).onErrorResume(WebClientResponseException.class,
						ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
	}

}
