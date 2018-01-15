package io.github.cepr0.demo_jpa_rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * @author Cepr0, 2018-01-14
 */
@Entity
public class Child {

	@Id @GeneratedValue private Integer id;
	private String name;
	@ManyToOne private Parent parent;

	public Child() {
	}

	public Child(String name, Parent parent) {
		this.name = name;
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Child child = (Child) o;
		return Objects.equals(id, child.id) &&
				Objects.equals(name, child.name) &&
				Objects.equals(parent, child.parent);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, parent);
	}

	@Override
	public String toString() {
		return "Child{" +
				"id=" + id +
				", name='" + name + '\'' +
				", parent=" + parent +
				'}';
	}
}
