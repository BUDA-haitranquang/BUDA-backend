DELIMITER //
CREATE PROCEDURE product_left_log_notification()
BEGIN
	DECLARE i int DEFAULT 1;
    WHILE i <= (SELECT MAX(user_id) from user) DO
		IF ((SELECT COUNT(*) FROM product_left_log where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)) > 0) 
        THEN
		INSERT INTO notification(creation_time, message, seen, user_id) 
        values (now(), concat('There are ', (SELECT COUNT(*) FROM product_left_log where user_id = i and creation_time >= (now() - INTERVAL 24 HOUR)), ' new product log(s)'), false, i);
        END IF;
        SET i = i + 1;
	END while;
END //

CREATE event product_left_log_notification_event
ON SCHEDULE every 24 HOUR
DO
  CALL product_left_log_notification