# fares-api

## Introduction

This project is an RESTful API project in order to retrieve fare price based on origin/destination input codes which is consuming mocked services like oauth token & fares API from backend. 

Other endpoint is to retrieve airport details based on input code which will be consuming mocked services like oauth token & airports API from backend. 

## Key Features

	Spring Boot v2.0.4.RELEASE Application
	Java 8 features
	H2 in-memory Database
	Centralized Exception Handling
	Single Error JSON Bean
	Input validations
	log4j2 with Rolling file appender
	Junits

## Build the project

Build the jar by using below command in root folder.
	
	mvn clean package
	
## Run FareApiApplication(Main class) as a Java application from eclipse

Run as Java application on below class 
	
	FareApiApplication.java
	
## Run as standalone

Run below command to run application as a standalone
	
	java -jar target/fares-api-0.0.1-SNAPSHOT.jar

## PostMan Endpoint

**Retrieve a fare offer**:

GET Request for http://localhost:8081/fare-price?origin=YOW&destination=BBA

Query params:
- origin: the requested string
- destination: the requested string

**Retrieve a specific airport**:

GET http://localhost:8081/fare-airports?code=yow

Query params:
- code: the requested string location code

**Retrieve oauth access token dynamically**:

Mocked URL
Post http://localhost:8080/oauth/token?grant_type=client_credentials
HTTP Headers parameters 
Authorization: Basic dHJhdmVsLWFwaS1jbGllbnQ6cHN3

**Retrieve API stats**:
