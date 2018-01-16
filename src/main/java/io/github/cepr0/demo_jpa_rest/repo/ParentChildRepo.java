package io.github.cepr0.demo_jpa_rest.repo;

import io.github.cepr0.demo_jpa_rest.dto.ParentDto;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Cepr0, 2018-01-16
 */
@Repository
public class ParentChildRepo {

	@Autowired private ParentRepo parentRepo;
	@PersistenceContext private EntityManager em;

	public Page<ParentDto> getPatentDto(Pageable pageable) {
		//noinspection unchecked
		return new PageImpl<>(em.createNativeQuery("select " +
				"  pc.id as \"id\", " +
				"  pc.name as \"name\", " +
				"  pc.children as \"children\" " +
				"from ( select " +
				"  p.id as id, " +
				"  p.name as name, " +
				"  cg.child as children, " +
				"  row_number() over() as rowNum " +
				"from " +
				"  parent p " +
				"  inner join (select c.parent_id, concat('', json_agg(json_build_object('id', c.id, 'name', c.name))) as child from child c group by c.parent_id) cg on cg.parent_id = p.id " +
				" ) pc where " +
				"  pc.rowNum between ?1 + 1 and ?1 + ?2")
				.setParameter(1, pageable.getOffset())
				.setParameter(2, pageable.getPageSize())
				.unwrap(NativeQuery.class)
				.setResultTransformer(Transformers.aliasToBean(ParentDto.class))
				.getResultList(), pageable, parentRepo.count());
	}
}
