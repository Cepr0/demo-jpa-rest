package io.github.cepr0.demo_jpa_rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author Cepr0, 2018-01-03
 */
@RepositoryRestResource(exported = false)
public interface PersonRepo extends JpaRepository<Person, Integer> {

	Optional<Person> findById(Integer id);
}
