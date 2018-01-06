package io.github.cepr0.demojpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Cepr0, 2018-01-03
 */
@RepositoryRestResource(exported = false)
public interface PersonRepo extends JpaRepository<Person, Integer> {
}
