package com.fare.statistics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class FareApiApplication {

	@Value("${spring.data.mongodb.uri}")
	private String resource;

	public static void main(String[] args) {
		SpringApplication.run(FareApiApplication.class, args);
	}

	@Bean
	public Mongo mongo() throws Exception {
		MongoClientOptions.Builder clientOptions = new MongoClientOptions.Builder();
		clientOptions.minConnectionsPerHost(1);
		clientOptions.connectionsPerHost(1);
		return new MongoClient(new MongoClientURI(resource, clientOptions));
	}

}
