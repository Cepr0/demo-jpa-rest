package io.github.cepr0.demo_jpa_rest;

import io.github.cepr0.demo_jpa_rest.model.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepr0, 2018-01-03
 */
public class PersonTest {

	@Test
	public void newPerson() {
		Person person = Person.of("Person1", "Address1");
		assertThat(person).isNotNull();
		assertThat(person.getName()).isEqualTo("Person1");
		assertThat(person.getAddress()).isEqualTo("Address1");
		System.out.println(person);
	}

	@Test
	public void copyPerson() {
		Person person = Person.of("Person1", "Address1");
		Person copyOfPerson = Person.copyOf(person);
		assertThat(copyOfPerson).isNotNull();
		assertThat(copyOfPerson.getName()).isEqualTo(person.getName());
		assertThat(copyOfPerson.getAddress()).isEqualTo(person.getAddress());
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenNullPersonWhenIllegalArgumentException() {
		//noinspection ConstantConditions
		Person copyOfPerson = Person.copyOf(null);
	}
}