
package com.fare.statistics.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
/**
 * This Model class is to bind with airport information received from mocked Airport API.
 * @author Manish
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "name", "description", "coordinates", "parent" })
@Data
public class Location {

	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("coordinates")
	private Coordinates coordinates;
	@JsonProperty("parent")
	private Parent parent;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("coordinates")
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@JsonProperty("coordinates")
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@JsonProperty("parent")
	public Parent getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
