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

import com.fare.statistics.models.FareModel;
import com.fare.statistics.oauth.TokenGenerator;

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

	public FareServiceImpl() {
		client = WebClient.builder().baseUrl(resource).build();
	}

	public Mono<FareModel> retrieveRates(String origin, String destination) {
		String token = tokenGenerator.generateToken();
		String uriString = UriComponentsBuilder.fromHttpUrl(resource).path("/" + origin).path("/" + destination)
				.toUriString();
		log.info("generated URI string is: {}" + uriString);
		WebClient.ResponseSpec responseSpec = client.get().uri(uriString).accept(MediaType.APPLICATION_STREAM_JSON)
				.header(AUTHORIZATION, "Bearer " + token).retrieve();

		return responseSpec.onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new Exception("500 error!")))
				.bodyToMono(FareModel.class).onErrorResume(WebClientResponseException.class,
						ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
	}

}
