package com.fare.statistics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fare.statistics.domains.ResponseDetails;
import com.fare.statistics.repositories.APIStatsRepository;

/**
 * This class is used to provide API statistics stored in Mongo DB for each API
 * hits.
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private APIStatsRepository repository;

	@Override
	public String getApiStats() {

		List<ResponseDetails> list = repository.findAll();
		long totalCount = list.stream().count();
		int min = list.stream().mapToInt(a -> a.getResponseTime()).min().orElse(0);
		int max = list.stream().mapToInt(a -> a.getResponseTime()).max().orElse(0);
		double average = list.stream().mapToInt(a -> a.getResponseTime()).average().orElse(0);
		long okCount = list.stream().filter(res -> (200 == res.getStatusCode())).count();
		long fourxxCount = list.stream().filter(res -> (404 == res.getStatusCode())).count();
		long fivexxCount = list.stream().filter(res -> (500 == res.getStatusCode())).count();
		return "Total number of requests processed: " + totalCount + "\n" + "Count(OK response) : " + okCount + "\n"
				+ "Count(4xx response) : " + fourxxCount + "\n" + "Count(5xx response) : " + fivexxCount + "\n"
				+ "Minimum response time: " + min + "\n" + "Maximum response time: " + max + "\n"
				+ "Average response time: " + average + "\n";

	}

}
