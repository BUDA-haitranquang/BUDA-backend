DELIMITER //
CREATE PROCEDURE ingredient_left_log_notification()
BEGIN
	DECLARE i int DEFAULT 1;
    WHILE i <= (SELECT MAX(user_id) from user) DO
		IF ((SELECT COUNT(*) FROM ingredient_left_log where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)) > 0) 
        THEN
		INSERT INTO notification(creation_time, message, seen, user_id) 
        values (now(), concat('There are ', (SELECT COUNT(*) FROM ingredient_left_log where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)), ' new ingredient log(s)'), false, i);
        END IF;
        SET i = i + 1;
	END while;
END //

CREATE event ingredient_left_log_notification_event
ON SCHEDULE every 24 HOUR
DO
  CALL ingredient_left_log_notification