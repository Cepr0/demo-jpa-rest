package io.github.cepr0.demo_jpa_rest;

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
@Profile("dev")
@RequiredArgsConstructor
@Component
public class DemoData {

	@NonNull private final PersonRepo personRepo;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		personRepo.save(asList(
				Person.of("Person1", "Address1"),
				Person.of("Person2", "Address2"),
				Person.of("Person3", "Address3")
		));
	}
}
