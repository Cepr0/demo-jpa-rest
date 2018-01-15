package io.github.cepr0.demo_jpa_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * @author Cepr0, 2018-01-14
 */
@Entity
public class Parent {

	@Id @GeneratedValue private Integer id;
	private String name;
	@JsonIgnoreProperties("parent")
	@OneToMany(mappedBy = "parent") private List<Child> children;

	public Parent() {
	}

	public Parent(String name) {
		this.name = name;
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

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Parent parent = (Parent) o;
		return Objects.equals(id, parent.id) &&
				Objects.equals(name, parent.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Parent{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
