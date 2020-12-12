/**
 * 
 */
package com.fare.statistics.services;

import com.fare.statistics.models.Location;

import reactor.core.publisher.Mono;

/**
 * @author Manish
 *
 */
public interface AirportService {

	public Mono<Location> retrieveRoutes(String location);

}
