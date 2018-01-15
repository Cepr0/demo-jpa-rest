package io.github.cepr0.demo_jpa_rest.controller;

import io.github.cepr0.demo_jpa_rest.dto.ParentProjection;
import io.github.cepr0.demo_jpa_rest.repo.ParentRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@NonNull private final ParentRepo parentRepo;

	@GetMapping("/parents")
	public ResponseEntity<?> getAll(Pageable pageable) {
		Page<ParentProjection> parentPage = parentRepo.getWithJson(pageable);
		return ResponseEntity.ok(parentPage);
//		return ResponseEntity.ok(parentPage.getContent());
//		return ResponseEntity.ok(parentRepo.findAll());
	}
}
