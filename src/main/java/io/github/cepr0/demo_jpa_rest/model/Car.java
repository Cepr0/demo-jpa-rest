package io.github.cepr0.demo_jpa_rest.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

/**
 * @author Cepr0, 2018-01-13
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@Entity
public class Car extends BaseEntity {

	@ManyToOne(fetch = LAZY)
	private Person person;
	
	private final String model;
	private final String brand;

	public static Car copyOf(@NonNull Car car) {
		return Car.of(car.getModel(), car.getBrand());
	}
}
