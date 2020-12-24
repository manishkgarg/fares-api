# fares-api

## Introduction

This project is an RESTful API project in order to retrieve fare price based on origin/destination input codes which consumes running mocked services like oauth token & fares API from backend. 

Second endpoint is to retrieve airports JSON information details which will consumes mocked services like oauth token & airports API from backend.

Third API is to provide statistics information for above API hits like
count(requests processed)  
count(OK response status code)
count(4xx response)
count(5xx response)
min(ResponseTime)
max(ResponseTime)
average(ResponseTime)

## Key Features

	Spring Boot v2.0.4.RELEASE Application
	Java 8 features
	MongoDB Atlas Version 4.2.11 Database
	Rest API using reactive Programming
	Centralized Exception Handling
	Single Error JSON Bean
	Input validations with proper Regex
	log4j2 with Rolling file appender
	JUnits

## Build the project

Build the jar by using below command in root folder.
	
	mvn clean install
	
## Run FareApiApplication(Main class) as a Java application from eclipse

Run as Java application on below class 
	
	FareApiApplication.java
	
## Run as standalone

Run below command to run application as a standalone
	
	java -jar target/fares-api-0.0.1-SNAPSHOT.jar

## PostMan Endpoint

**Retrieve a fare offer**:

GET Request for 
http://localhost:8081/fare-price?origin=YOW&destination=BBA

Query params:
- origin: the requested string
- destination: the requested string

**Retrieve airport detailed information JSON**:

GET Request for
http://localhost:8081/fare-airports


**Retrieve API stats**:

GET Request for
http://localhost:8081/fare-statistics


**Retrieve oauth access token dynamically**:

Mocked URL
Post http://localhost:8080/oauth/token?grant_type=client_credentials
HTTP Headers parameters 
Authorization: Basic dHJhdmVsLWFwaS1jbGllbnQ6cHN3
