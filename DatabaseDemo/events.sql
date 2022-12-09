CREATE EVENT daily_customer_total_spend_update
ON schedule 
EVERY 1 HOUR
DO
update customer AS c
inner join 
(select customer_id, sum(final_cost) as res from sell_order group by customer_id) AS s
on c.customer_id = s.customer_id
set c.total_spend = s.res;