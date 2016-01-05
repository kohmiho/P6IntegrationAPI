with p6Test as (
  select proj_id, proj_short_name from adm4161jl02.project@loadspring_link where orig_proj_id is null and project_flag='Y'
),
p6Prod as (
   select proj_id, proj_short_name from adm4161jl01.project@loadspring_prod_link where orig_proj_id is null and project_flag='Y'
)
, allP6 as (
  select p6Test.proj_id as p6Test_proj_id, p6Test.proj_short_name as p6Test_proj_short_name,
  p6Prod.proj_id as p6Prod_proj_id, p6Prod.proj_short_name as p6Prod_proj_short_name,
  case when p6Test.proj_id = p6Prod.proj_id then 'In both DB'
    when p6Test.proj_id is null then 'In Prod DB only'
    when p6Prod.proj_id is null then 'In Test DB only'
    end as remark
  from p6Test full join p6Prod on p6Test.proj_id=p6Prod.proj_id
)
select allP6.*
from allP6 order by remark, p6Prod_proj_id, p6Test_proj_id;