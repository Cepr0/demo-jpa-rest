package io.github.cepr0.demo_jpa_rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cepr0.demo_jpa_rest.dto.ChildDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Cepr0, 2018-01-16
 */
@Service
public class ChildService {

	private final ObjectMapper mapper;

	public ChildService(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public List<ChildDto> buildChildren(String jsonChildren) throws IOException {
		return asList(mapper.readValue(jsonChildren, ChildDto[].class));
	}
}
