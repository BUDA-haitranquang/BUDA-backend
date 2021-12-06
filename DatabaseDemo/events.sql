CREATE EVENT daily_customer_total_spend_update
ON schedule 
EVERY 1 HOUR
DO
update customer AS c
inner join 
(select customer_id, sum(final_cost) as res from sell_order group by customer_id) AS s
on c.customer_id = s.customer_id
set c.total_spend = s.res;

CREATE EVENT daily_discount_order_count_update
ON Schedule
EVERY 5 second
DO 
update discount AS d
inner join
(select discount_id, count(distinct sell_order_id) as res from sell_order group by discount_id) as s
on d.discount_id = s.discount_id
set d.order_count = s.res;

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