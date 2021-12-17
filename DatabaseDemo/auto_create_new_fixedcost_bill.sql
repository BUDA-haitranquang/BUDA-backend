CREATE EVENT IF NOT EXISTS auto_create_new_fixedcostbill
ON SCHEDULE EVERY 1 DAY
DO 
INSERT INTO fixed_cost_bill(creation_time, due_time, message, status, total_spend, user_id, fixed_cost_id)
select curdate(), date_add(curdate(), interval fc.period day), fc.name, 'PREPARING', fcb.total_spend, fcb.user_id, fcb.fixed_cost_id from fixed_cost_bill as fcb 
inner join fixed_cost as fc on fcb.fixed_cost_id = fc.fixed_cost_id
where fc.visible = true 
and fcb.status = 'FINISHED' 
and day(date_add(fcb.creation_time, interval fc.period day)) = day(curdate()) 
and not exists (select * from fixed_cost_bill as fcb1 where fcb1.fixed_cost_id = fcb.fixed_cost_id and fcb1.status = 'PREPARING')
;


DROP EVENT IF EXISTS auto_create_new_fixedcostbill;