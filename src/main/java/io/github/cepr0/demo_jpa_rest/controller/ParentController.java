package io.github.cepr0.demo_jpa_rest.controller;

import io.github.cepr0.demo_jpa_rest.dto.ParentDto;
import io.github.cepr0.demo_jpa_rest.repo.ParentChildRepo;
import io.github.cepr0.demo_jpa_rest.repo.ParentRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cepr0, 2018-01-14
 */
@RequiredArgsConstructor
@RestController
@Transactional
public class ParentController {

	@NonNull private final PagedResourcesAssembler<ParentDto> assembler;
	@NonNull private final ParentRepo parentRepo;
	@NonNull private final ParentChildRepo parentChildRepo;

	@GetMapping("/parents")
	public ResponseEntity<?> getAll(Pageable pageable) {
		Page<ParentDto> parentPage = parentChildRepo.getPatentDto(pageable);
		return ResponseEntity.ok(assembler.toResource(parentPage));
	}
}
