DELIMITER //
CREATE PROCEDURE alert_near_duetime_fixedcost_bill_notification()
BEGIN 
	DECLARE i int DEFAULT 1;
    WHILE i <= (select MAX(fixed_cost_bill_id) from fixed_cost_bill) DO 
		insert into notification(creation_time, message, seen, user_id)
        select now(), concat('Fixed cost bill with ID ', i, ' need to pay after 7 days!!. With message ', (select message from fixed_cost_bill where fixed_cost_bill_id = i)), true, (select user_id from fixed_cost_bill where fixed_cost_bill_id = i) 
        from fixed_cost_bill as fcb 
        where fcb.fixed_cost_bill_id = i 
        and fcb.status = 'PREPARING'
        and day(date_add(now(), interval 7 day)) = day(fcb.due_time);
        SET i = i + 1;
	END while;
END //
DELIMITER ;

CREATE EVENT alert_near_duetime_fixedcost_bill_notification_event
ON SCHEDULE EVERY 24 HOUR
DO
CALL alert_near_duetime_fixedcost_bill_notification;


