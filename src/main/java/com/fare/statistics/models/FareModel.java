package com.fare.statistics.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Model class is to bind with fare information json received from mocked
 * Fare API.
 * 
 * @author Manish
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FareModel {

	private Double amount;
	private String currency;
	private String origin;
	private String destination;

}
