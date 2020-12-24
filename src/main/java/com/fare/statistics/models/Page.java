
package com.fare.statistics.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

/**
 * This Model class is to bind with Page information.
 * 
 * @author Manish
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "size", "totalElements", "totalPages", "number" })
@Builder
@Data
public class Page {

	@JsonProperty("size")
	private Integer size;
	@JsonProperty("totalElements")
	private Integer totalElements;
	@JsonProperty("totalPages")
	private Integer totalPages;
	@JsonProperty("number")
	private Integer number;

}
