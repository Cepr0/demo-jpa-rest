package io.github.cepr0.demo_jpa_rest;

import org.joor.Reflect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Cepr0, 2018-01-06
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired private MockMvc mvc;
	@MockBean private PersonRepo personRepo;
	private List<Person> people;

	@Before
	public void setUp() throws Exception {
		people = asList(
				Person.of("Person1", "Address1"),
				Person.of("Person2", "Address2"),
				Person.of("Person3", "Address3"),
				Person.of("Person4", "Address4")
		);

		for (int i = 0; i < people.size(); i++) {
			Reflect.on(people.get(i)).set("id", i + 1);
		}
	}

	@Test
	public void getAll() throws Exception {
		given(personRepo.findAll()).willReturn(people);
		mvc.perform(get("/people")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(people.get(0).getId())))
				.andExpect(jsonPath("$[3].id", is(people.get(3).getId())))
				.andExpect(jsonPath("$[0].name", is(people.get(0).getName())))
				.andExpect(jsonPath("$[3].name", is(people.get(3).getName())))
				.andExpect(jsonPath("$[0].address", is(people.get(0).getAddress())))
				.andExpect(jsonPath("$[3].address", is(people.get(3).getAddress())));
	}

	@Test
	public void getOne() throws Exception {
		given(personRepo.findById(1)).willReturn(Optional.of(people.get(0)));
		given(personRepo.findById(4)).willReturn(Optional.of(people.get(3)));
		given(personRepo.findById(5)).willReturn(Optional.empty());

		mvc.perform(get("/people/1")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(people.get(0).getId())))
				.andExpect(jsonPath("$.name", is(people.get(0).getName())))
				.andExpect(jsonPath("$.address", is(people.get(0).getAddress())));

		mvc.perform(get("/people/4")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(people.get(3).getId())))
				.andExpect(jsonPath("$.name", is(people.get(3).getName())))
				.andExpect(jsonPath("$.address", is(people.get(3).getAddress())));

		mvc.perform(get("/people/5")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().string(isEmptyString()));
	}

	@Test
	public void create() throws Exception {
		Person person = Person.of("Person5", "Address5");
		Person person5 = Person.copyOf(person);
		given(personRepo.save(person)).willReturn(Reflect.on(person5).set("id", 5).get());

		mvc.perform(post("/people")
				.contentType(APPLICATION_JSON)
				.content("{\"name\": \"Person5\", \"address\": \"Address5\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(person5.getId())))
				.andExpect(jsonPath("$.name", is(person5.getName())))
				.andExpect(jsonPath("$.address", is(person5.getAddress())));
	}

	@Test
	public void delete() throws Exception {
		doNothing().when(personRepo).delete(1);

		mvc.perform(MockMvcRequestBuilders.delete("/people/1")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(content().string(isEmptyString()));
	}
}