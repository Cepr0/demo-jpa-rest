package io.github.cepr0.demo_jpa_rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepr0, 2018-01-03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepoTest {

	private static final String NAME = "Vasya";
	private static final String ADDRESS = "Moldovanka 1";

	@Autowired private PersonRepo personRepo;

	@Test
	public void save() {
		personRepo.save(Person.of(NAME, ADDRESS));
		List<Person> people = personRepo.findAll();
		assertThat(people).isNotNull();
		assertThat(people).hasSize(1);
		assertThat(people.get(0).getName()).isEqualTo(NAME);
		assertThat(people.get(0).getAddress()).isEqualTo(ADDRESS);
		System.out.println(people);
	}
}