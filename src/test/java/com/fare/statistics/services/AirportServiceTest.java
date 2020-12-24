package com.fare.statistics.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fare.statistics.models.Root;
import com.fare.statistics.oauth.TokenGenerator;
import com.fare.statistics.repositories.APIStatsRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
public class AirportServiceTest {

	@InjectMocks
	private AirportServiceImpl airportService;

	@Mock
	private WebClient webClient;

	@Mock
	private TokenGenerator tokenGenerator;

	@Mock
	private APIStatsRepository repository;
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(airportService, "resource", "http://localhost:8080/airports", String.class);
		WebClient.RequestHeadersUriSpec requestHeadersUriMock = mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersMock = mock(WebClient.RequestHeadersSpec.class);
		when(this.webClient.get()).thenReturn(requestHeadersUriMock);
		when(this.tokenGenerator.generateToken()).thenReturn("abc");
		WebClient.RequestHeadersSpec<?> uri = this.webClient.get().uri(any(String.class));
		when(uri).thenReturn(requestHeadersMock);
		WebClient.RequestHeadersSpec<?> accepts = requestHeadersMock.accept(MediaType.APPLICATION_STREAM_JSON);
		when(accepts).thenReturn(mock(WebClient.RequestHeadersSpec.class));
	}

	@Test(expected = NullPointerException.class)
	public void testAirportService() throws Exception {
		when(this.webClient.get().uri("http://localhost:8080/airports/yow")
				.header("Authorization", tokenGenerator.generateToken()).accept(MediaType.APPLICATION_STREAM_JSON)
				.retrieve()).thenReturn(createResponseSpec(HttpStatus.OK));
		Mono<Root> result = airportService.retrieveRoutes();
		assertNotNull("result must not be empty", result);
	}

	private WebClient.ResponseSpec createResponseSpec(HttpStatus httpStatus) {
		Constructor<?> declaredConstructor;
		try {
			declaredConstructor = Class
					.forName("org.springframework.web.reactive.function.client.DefaultWebClient$DefaultResponseSpec")
					.getDeclaredConstructor(Mono.class, Supplier.class);
			ClientResponse.Builder responseBuilder = ClientResponse.create(httpStatus);
			Mono<ClientResponse> responseMono = Mono.just(responseBuilder.build());
			Supplier<HttpRequest> requestSupplier = () -> mock(HttpRequest.class);
			return (WebClient.ResponseSpec) declaredConstructor.newInstance(responseMono, requestSupplier);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException
				| InstantiationException | InvocationTargetException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}