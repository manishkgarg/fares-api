/**
 * 
 */
package com.fare.statistics.services;

import com.fare.statistics.models.Root;

import reactor.core.publisher.Mono;

/**
 * @author Manish
 *
 */
public interface AirportService {

	public Mono<Root> retrieveRoutes();

}
