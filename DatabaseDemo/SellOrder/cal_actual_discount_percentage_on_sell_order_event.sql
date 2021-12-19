
-- use db_example; 
CREATE EVENT IF NOT EXISTS cal_actual_discount_percentage_on_sell_order
ON SCHEDULE EVERY 1 HOUR
DO 
UPDATE sell_order 
SET sell_order.actual_discount_cash = sell_order.real_cost - sell_order.final_cost,
	sell_order.actual_discount_percentage = ROUND((sell_order.real_cost - sell_order.final_cost) / sell_order.real_cost, 2) 
WHERE 
	sell_order.actual_discount_cash is null or sell_order.actual_discount_percentage is null;

DROP EVENT IF EXISTS cal_actual_discount_percentage_on_sell_order;
SHOW EVENTS FROM db_example;