CREATE EVENT daily_membership_update
on schedule
EVERY 1 HOUR
DO 
Update customer AS c 
Set membership_id=
(select membership_type_id from membership_type as m
where m.user_id=c.user_id
and c.total_spend-m.minimum_spend>0
order by c.total_spend-m.minimum_spend limit 1);