package io.github.cepr0.demo_jpa_rest;

import io.github.cepr0.demo_jpa_rest.model.Person;
import io.github.cepr0.demo_jpa_rest.repo.PersonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2018-01-05
 */
@Profile("test")
@RequiredArgsConstructor
@Component
public class TestData {

	@NonNull private final PersonRepo personRepo;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		personRepo.save(asList(
				Person.of("TestPerson1", "TestAddress1"),
				Person.of("TestPerson2", "TestAddress2")
		));
	}
}
