package io.github.cepr0.demo_jpa_rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepr0, 2018-01-03
 */
@ActiveProfiles("test")
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepoTest {

	private static final String NAME = "Test Person";
	private static final String ADDRESS = "Test Address";

	@Autowired private PersonRepo personRepo;
	@Autowired private EntityManager entityManager;

	@Test
	public void save() {
		Person person3 = personRepo.save(Person.of(NAME, ADDRESS));
		List<Person> people = personRepo.findAll();
		assertThat(people).isNotNull();
		assertThat(people).hasSize(3);
		assertThat(people.get(2).getId()).isEqualTo(person3.getId());
		assertThat(people.get(2).getName()).isEqualTo(person3.getName());
		assertThat(people.get(2).getAddress()).isEqualTo(person3.getAddress());
		System.out.println(people);
	}

	@Test
	public void findById() {
		Person person3 = Person.of(NAME, ADDRESS);
		entityManager.persist(person3);
		entityManager.flush();

		Person person = personRepo.findById(3).orElseThrow(RuntimeException::new);
		assertThat(person.getId()).isEqualTo(person3.getId());
		assertThat(person.getName()).isEqualTo(person3.getName());
		assertThat(person.getAddress()).isEqualTo(person3.getAddress());
	}
}