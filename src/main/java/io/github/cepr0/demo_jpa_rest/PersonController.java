package io.github.cepr0.demo_jpa_rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Cepr0, 2018-01-05
 */
@RequiredArgsConstructor
@RestController
public class PersonController {

	@NonNull private final PersonRepo personRepo;

	@GetMapping("/people")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(personRepo.findAll());
	}

	@GetMapping("/people/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Integer id) {
		return personRepo.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/people")
	public ResponseEntity<?> create(@RequestBody Person person) {
		Person person1 = personRepo.save(person);
		URI uri = ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getOne(person1.getId())).toUri();
		return ResponseEntity.created(uri).body(person1);
	}

	@DeleteMapping("/people/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		personRepo.delete(id);
		return ResponseEntity.noContent().build();
	}
}
