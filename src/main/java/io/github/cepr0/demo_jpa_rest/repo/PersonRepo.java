package io.github.cepr0.demo_jpa_rest.repo;

import io.github.cepr0.demo_jpa_rest.dto.PersonProjection;
import io.github.cepr0.demo_jpa_rest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Cepr0, 2018-01-03
 */
@RepositoryRestResource(exported = false)
public interface PersonRepo extends JpaRepository<Person, Integer> {

//	@EntityGraph(attributePaths = "cars")
	@Query("select distinct p from Person p left join fetch p.cars")
	@Override
	List<Person> findAll();

//	@EntityGraph(attributePaths = "cars")
	@Query("select p from Person p left join fetch p.cars")
	Set<PersonProjection> findBy();

	Optional<Person> findById(Integer id);
}
