CREATE EVENT daily_discount_order_count_update
ON Schedule
EVERY 5 second
DO 
update discount AS d
inner join
(select discount_id, count(distinct sell_order_id) as res from sell_order group by discount_id) as s
on d.discount_id = s.discount_id
set d.order_count = s.res;