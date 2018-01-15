package io.github.cepr0.demo_jpa_rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.cepr0.demo_jpa_rest.model.Car;

import java.util.List;

/**
 * @author Cepr0, 2018-01-14
 */
public interface PersonProjection {
//	@Value("#{target.id}")
	Integer getId();
	String getName();
	String getAddress();

	@JsonIgnoreProperties("person")
//	@Value("#{target.cars}")
	List<Car> getCars();
}
