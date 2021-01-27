package com.fare.statistics.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Model is to bind token response generated from mocked oauth URL.
 * 
 * @author Manish
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenResponse {

	private String access_token;
	private String token_type;
	private int expires_in;
	private String scope;

	
}
