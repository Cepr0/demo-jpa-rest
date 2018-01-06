package io.github.cepr0.demojpa;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2018-01-05
 */
@RequiredArgsConstructor
@Component
public class DemoData {

	@NonNull private final PersonRepo personRepo;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		personRepo.save(asList(
				Person.of("Person1", "Address1"),
				Person.of("Person2", "Adsress2"),
				Person.of("Person3", "Adsress3")
		));
	}
}
