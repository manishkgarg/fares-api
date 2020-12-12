/**
 * 
 */
package com.fare.statistics.oauth;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fare.statistics.models.AccessTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is to generate access token dynamically using provided Oauth URL.
 * 
 * @author Manish
 *
 */
@Component
public class TokenGenerator {

	private static final String BASIC = "Basic ";

	private static final String AUTHORIZATION = "Authorization";

	private static final Logger log = LogManager.getLogger(TokenGenerator.class);

	@Value("${oauth.credentials}")
	private String credentials;

	@Value("${oauth.url}")
	private String access_token_url;

	public String generateToken() {

		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = null;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(AUTHORIZATION, BASIC + encodedCredentials);
		HttpEntity<String> request = new HttpEntity<>(headers);
		try {
			response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
			AccessTokenResponse tokenResponse = mapper.readValue(response.getBody(), AccessTokenResponse.class);
			log.info("Access Token response generated is {}" + tokenResponse);
			return tokenResponse.getAccess_token();
		} catch (IOException e) {
			log.error("Exception caught in TokenGenerator class {}" + e);
		}
		return null;
	}
}
