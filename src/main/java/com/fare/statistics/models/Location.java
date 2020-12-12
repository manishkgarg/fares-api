package com.fare.statistics.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This Model class is to bind with airport code information json received from mocked Airport API.
 * @author Manish
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	public String code;
	public String name;
	public String description;
	public Coordinates coordinates;
	public Parent parent;

}
