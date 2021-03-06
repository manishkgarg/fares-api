
package com.fare.statistics.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;
/**
 * This Model class is to bind root information.
 * @author Manish
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_embedded", "page" })
@Builder
@Data
public class Root {

	@JsonProperty("_embedded")
	private Embedded embedded;
	@JsonProperty("page")
	private Page page;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("_embedded")
	public Embedded getEmbedded() {
		return embedded;
	}

	@JsonProperty("_embedded")
	public void setEmbedded(Embedded embedded) {
		this.embedded = embedded;
	}

	@JsonProperty("page")
	public Page getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(Page page) {
		this.page = page;
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
