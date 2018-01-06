package io.github.cepr0.demojpa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Cepr0, 2018-01-03
 */
public class PersonTest {

	@Test
	public void setName() {
		Person person = Person.of("Person1", "Address1");
		System.out.println(person);
	}
}