DELIMITER //
CREATE PROCEDURE fixed_cost_bill_notification()
BEGIN
	DECLARE i int DEFAULT 1;
    WHILE i <= (SELECT MAX(user_id) from user) DO
		IF ((SELECT COUNT(*) FROM fixed_cost_bill where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)) > 0) 
        THEN
		INSERT INTO notification(creation_time, message, seen, user_id) 
        values (now(), concat('There are ', (SELECT COUNT(*) FROM fixed_cost_bill where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)), ' new fixed cost bill(s)'), false, i);
        END IF;
        SET i = i + 1;
	END while;
END //

CREATE event fixed_cost_bill_notification_event
ON SCHEDULE every 24 HOUR
DO
  CALL fixed_cost_bill_notification