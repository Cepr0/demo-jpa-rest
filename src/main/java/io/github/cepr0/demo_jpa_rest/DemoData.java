package io.github.cepr0.demo_jpa_rest;

import io.github.cepr0.demo_jpa_rest.model.Car;
import io.github.cepr0.demo_jpa_rest.model.Child;
import io.github.cepr0.demo_jpa_rest.model.Parent;
import io.github.cepr0.demo_jpa_rest.model.Person;
import io.github.cepr0.demo_jpa_rest.repo.CarRepo;
import io.github.cepr0.demo_jpa_rest.repo.ChildRepo;
import io.github.cepr0.demo_jpa_rest.repo.ParentRepo;
import io.github.cepr0.demo_jpa_rest.repo.PersonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2018-01-05
 */
@Profile("dev")
@RequiredArgsConstructor
@Component
public class DemoData {

	@NonNull private final PersonRepo personRepo;
	@NonNull private final CarRepo carRepo;
	@NonNull private final ParentRepo parentRepo;
	@NonNull private final ChildRepo childRepo;

	@Async
	@Transactional
	@EventListener
	public void personData(ApplicationReadyEvent event) {
		List<Person> people = personRepo.save(asList(
				Person.of("Person1", "Address1"),
				Person.of("Person2", "Address2"),
				Person.of("Person3", "Address3")
		));

		carRepo.save(asList(
				Car.of("Car1", "Brand1").setPerson(people.get(0)),
				Car.of("Car2", "Brand1").setPerson(people.get(0)),
				Car.of("Car3", "Brand2").setPerson(people.get(1)),
				Car.of("Car4", "Brand2").setPerson(people.get(1)),
				Car.of("Car5", "Brand3").setPerson(people.get(2)),
				Car.of("Car6", "Brand3").setPerson(people.get(2))
		));
	}

	@Async
	@Transactional
	@EventListener
	public void parentData(ApplicationReadyEvent event) {
		List<Parent> parents = parentRepo.save(asList(
				new Parent("Parent1"),
				new Parent("Parent2"),
				new Parent("Parent3"),
				new Parent("Parent4"),
				new Parent("Parent5"),
				new Parent("Parent6"),
				new Parent("Parent7"),
				new Parent("Parent8"),
				new Parent("Parent9"),
				new Parent("Parent10")
		));

		childRepo.save(asList(
				new Child("Child1", parents.get(0)),
				new Child("Child2", parents.get(0)),
				new Child("Child3", parents.get(1)),
				new Child("Child4", parents.get(1)),
				new Child("Child5", parents.get(2)),
				new Child("Child6", parents.get(2)),
				new Child("Child7", parents.get(3)),
				new Child("Child8", parents.get(3)),
				new Child("Child9", parents.get(4)),
				new Child("Child10", parents.get(4)),
				new Child("Child11", parents.get(5)),
				new Child("Child12", parents.get(5)),
				new Child("Child13", parents.get(6)),
				new Child("Child14", parents.get(6)),
				new Child("Child15", parents.get(7)),
				new Child("Child16", parents.get(7)),
				new Child("Child17", parents.get(8)),
				new Child("Child18", parents.get(8)),
				new Child("Child19", parents.get(9)),
				new Child("Child20", parents.get(9))
		));
	}
}
