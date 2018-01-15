explain (analyse  ) select
  *
from
  (select
    p.*,
    c.*,
    dense_rank() over (order by p.id) rn
  from
    parent p
    left join child c on p.id = c.parent_id
  ) pc
where
  rn between 1 and 2
;

select
  c.parent_id,
  json_agg(json_build_object('id', c.id, 'name', c.name))
from
  child c
group by
  c.parent_id
order by
  c.parent_id;

select * from
  (select
    p.id as id,
    p.name as name,
    cg.child as children,
    row_number() over() as rowNum
  from
    parent p
    inner join (select c.parent_id, json_agg(json_build_object('id', c.id, 'name', c.name)) as child from child c group by c.parent_id) cg on cg.parent_id = p.id) as pc
where
  pc.rowNum between 1 and 20
;
select * from ( select p.id as id, p.name as name, cg.child as children, row_number() over()
as rowNum from parent p inner join (select c.parent_id, json_agg(json_build_object('id', c.id,
'name', c.name)) as child from child c group by c.parent_id) cg on cg.parent_id = p.id ) pc
where pc.rowNum between 1 and 20 limit 20;