DELIMITER //
CREATE PROCEDURE alert_amount_ingredient_notification()
BEGIN
	DECLARE i int DEFAULT 1;
    WHILE i <= (SELECT MAX(user_id) from user) DO
		IF ((SELECT COUNT(*) FROM ingredient where user_id = i and visible = 1 and amount_left < alert_amount_left) > 0)
        THEN
		INSERT INTO notification(creation_time, message, seen, user_id)
        VALUES (now(), concat('There are ', (SELECT COUNT(*) FROM ingredient where user_id = i and visible = 1 and amount_left < alert_amount_left), ' ingredient(s) that are nearly out of stock'), false, i);
        END IF;
		SET i = i + 1;
    END while;
END //

ALTER EVENT alert_amount_ingredient_notification_event
ON SCHEDULE EVERY 24 HOUR
DO
CALL alert_amount_ingredient_notification