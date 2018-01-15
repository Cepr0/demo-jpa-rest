package io.github.cepr0.demo_jpa_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * @author Cepr0, 2018-01-03
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Getter
@Entity
public class Person extends BaseEntity {

	private final String name;
	private final String address;

	@JsonIgnoreProperties("person")
	@OneToMany(mappedBy = "person", cascade = {PERSIST, MERGE})
	private final List<Car> cars = new ArrayList<>();

	public static Person copyOf(@NonNull Person person) {
		return Person.of(person.getName(), person.getAddress());
	}

	public void setCars(List<Car> cars) {
		cars.forEach(car -> car.setPerson(this));
		this.cars.forEach(car -> car.setPerson(null));
		this.cars.clear();
		this.cars.addAll(cars);
		}
}
