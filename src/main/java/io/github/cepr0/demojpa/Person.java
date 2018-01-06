package io.github.cepr0.demojpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Cepr0, 2018-01-03
 */
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Entity
public class Person {

	@Id
	@GeneratedValue
	private final Integer id;
	private String name;
	private String address;

	public static Person of(String name, String address) {
		return new Person().setName(name).setAddress(address);
	}
}
