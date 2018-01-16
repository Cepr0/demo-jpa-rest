package io.github.cepr0.demo_jpa_rest.dto;

import io.github.cepr0.demo_jpa_rest.model.Parent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * @author Cepr0, 2018-01-16
 */
//@Projection(name = "parent", types = Parent.class)
public interface ParentProjection {
	
	@Value("#{target.id}")
	Integer getId();
	
	@Value("#{target.name}")
	String getName();
	
	@Value("#{@childService.buildChildren(target.children)}")
	List<ChildDto> getChildren();
}
