package io.github.cepr0.demo_jpa_rest.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author Cepr0, 2018-01-16
 */
public interface ParentProjection {
	Integer getId();
	String getName();
	@Value("#{@childService.buildChildren(target.children)}")
	List<ChildDto> getChildren();
}
