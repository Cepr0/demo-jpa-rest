package io.github.cepr0.demo_jpa_rest.repo;

import io.github.cepr0.demo_jpa_rest.model.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author Cepr0, 2018-01-14
 */
@RepositoryRestResource(exported = false)
public interface CarRepo extends JpaRepository<Car, Integer> {

	@EntityGraph(attributePaths = "person")
	Optional<Car> findById(Integer id);
}
