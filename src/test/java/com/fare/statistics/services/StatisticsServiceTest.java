package com.fare.statistics.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.fare.statistics.domains.ResponseDetails;
import com.fare.statistics.repositories.APIStatsRepository;

@RunWith(SpringRunner.class)
public class StatisticsServiceTest {

	@InjectMocks
	private StatisticsServiceImpl statisticsService;

	@Mock
	private APIStatsRepository repository;

	@Test
	public void testApiStats() {
		List<ResponseDetails> list = new ArrayList<>();
		list.add(ResponseDetails.builder().statusCode(200).responseTime(145).build());
		list.add(ResponseDetails.builder().statusCode(404).responseTime(245).build());
		list.add(ResponseDetails.builder().statusCode(500).responseTime(500).build());
		when(this.repository.findAll()).thenReturn(list);
		String result = statisticsService.getApiStats();
		assertNotNull("result must not be empty", result);
	}

}
