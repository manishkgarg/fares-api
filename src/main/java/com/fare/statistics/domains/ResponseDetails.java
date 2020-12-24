/**
 * 
 */
package com.fare.statistics.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manish
 *
 */
@Data
@Builder
@Document(collection = "ResponseDetails")
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetails {
	@Id
	public String id;
	public String apiName;
	public String UUID;
	public int statusCode;
	public int responseTime;

}
