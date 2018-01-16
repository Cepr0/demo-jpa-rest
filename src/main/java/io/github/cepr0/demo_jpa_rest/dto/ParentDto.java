package io.github.cepr0.demo_jpa_rest.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cepr0, 2018-01-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDto {
	private Integer id;
	private String name;
	@JsonRawValue
	private String children;
}
