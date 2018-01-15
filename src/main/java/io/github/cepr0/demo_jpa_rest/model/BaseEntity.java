package io.github.cepr0.demo_jpa_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Cepr0, 2018-01-14
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Identifiable<Integer> {

	@Id
	@GeneratedValue
	private Integer id = null;

	@JsonIgnore
	@Version
	private long version;

//	@JsonIgnore
//	@Override
//	public boolean isNew() {
//		return getId() == null;
//	}

	@Override
	public String toString() {
		return "{id=" + id + "}";
	}
}
