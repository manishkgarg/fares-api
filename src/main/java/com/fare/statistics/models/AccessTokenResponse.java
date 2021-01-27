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

	private String accessToken;
	private String tokenType;
	private int expires;
	private String scope;

}
