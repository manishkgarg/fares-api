package com.fare.statistics.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * This is a model class that holds fare Request details.
 */
@Data
public class Request {

	@Pattern(regexp = "[a-zA-Z0-9]{3}", message = "The format of the value specified for field startLocation is invalid,format must match [a-zA-Z0-9]{4}")
	@NotBlank(message = "origin code should not be null")
	@JsonProperty(value = "origin", required = true)
	private String origin;

	@Pattern(regexp = "[a-zA-Z0-9]{3}", message = "The format of the value specified for field endLocation is invalid,,format must match [a-zA-Z0-9]{4}")
	@NotBlank(message = "destination code should not be null")
	@JsonProperty(value = "destination", required = true)
	private String destination;

}
