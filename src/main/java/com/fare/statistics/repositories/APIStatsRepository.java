package com.fare.statistics.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fare.statistics.domains.ResponseDetails;
/**
 * This class extends MongoRepository in order to retrieve and save API response details to Mongo DB.
 *
 */
public interface APIStatsRepository extends MongoRepository<ResponseDetails, String> {

	List<ResponseDetails> findByApiName(String name);
}
