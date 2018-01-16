package io.github.cepr0.demo_jpa_rest.repo;

import io.github.cepr0.demo_jpa_rest.dto.ParentProjection;
import io.github.cepr0.demo_jpa_rest.model.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Cepr0, 2018-01-14
 */
@RepositoryRestResource(exported = false)
public interface ParentRepo extends JpaRepository<Parent, Integer> {

//	@EntityGraph(attributePaths = "children")
	@Query("select distinct p as parent from Parent p left join fetch p.children c")
	@Override
	List<Parent> findAll();

//	@EntityGraph(attributePaths = "children")
//	@Query(value = "select distinct p as parent from Parent p left join fetch p.children c",
//			countQuery = "select count(p) from Parent p",
//			countProjection = "p.id"
//	)
	@Query(value =
			"select " +
			"  * " +
			"from" +
			"  (select *, dense_rank() over (order by p.id) as rowNum from parent p left join child c on p.id = c.parent_id ) as pc " +
			"where " +
			"  pc.rowNum between ?#{#pageable.offset + 1} and ?#{#pageable.offset + #pageable.pageSize}",
			countQuery = "select count(p.id) from parent p",
			nativeQuery = true)
	@Override
	Page<Parent> findAll(Pageable pageable);
	
	@Query(value = "select " +
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
			"  pc.rowNum between ?#{#pageable.offset + 1} and ?#{#pageable.offset + #pageable.pageSize}",
			countQuery = "select count(p.id) from parent p",
//			countProjection = "pc.id",
			nativeQuery = true)
	Page<ParentProjection> getWithJson(Pageable pageable);
}
