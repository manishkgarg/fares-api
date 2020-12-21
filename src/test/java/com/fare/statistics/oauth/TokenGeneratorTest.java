package com.fare.statistics.oauth;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.fare.statistics.models.AccessTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class TokenGeneratorTest {

	@InjectMocks
	private TokenGenerator tokenGenerator;

	@Mock
	private RestTemplate restTemplate;

	HttpEntity<String> request;
	ResponseEntity<String> response;
	ObjectMapper mapper;

	@Before
	public void setup() {
		ReflectionTestUtils.setField(tokenGenerator, "credentials", "travel-api-client:psw", String.class);
		ReflectionTestUtils.setField(tokenGenerator, "oauthUrl",
				"http://localhost:8080/oauth/token?grant_type=client_credentials", String.class);
		request = mock(HttpEntity.class);
		response = mock(ResponseEntity.class);
		mapper = mock(ObjectMapper.class);
	}

	@Test
	public void testTokenGenerator() throws IOException {
		AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
		accessTokenResponse.setAccess_token("dnkdnkjd-6bd77-djnid78-djbjhd7-jhbddhdjbjdbh");
		when(this.restTemplate.exchange("http://localhost:8080/oauth/token?grant_type=client_credentials",
				HttpMethod.POST, request, String.class)).thenReturn(response);
		when(mapper.readValue(response.getBody(), AccessTokenResponse.class)).thenReturn(accessTokenResponse);
		String result = tokenGenerator.generateToken();
		assertNotNull("result must not be empty", result);
	}

}