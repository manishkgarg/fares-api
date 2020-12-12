/**
 * 
 */
package com.fare.statistics.services;

import com.fare.statistics.models.FareModel;

import reactor.core.publisher.Mono;

/**
 * @author Manish
 *
 */
public interface FareService {

	public Mono<FareModel> retrieveRates(String origin, String destination);
}
