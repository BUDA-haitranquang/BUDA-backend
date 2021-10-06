drop trigger if exists creating_user_trigger;
DELIMITER //
create trigger creating_user_trigger after insert on user
for each row
BEGIN
	insert into customer(name, age_group, gender, phone_number, user_id) values ("Unknown", "UNKNOWN", "UNKNOWN", "UNKNOWN", NEW.user_id);
END;


drop trigger if exists creating_sell_order_trigger;

create trigger creating_sell_order_trigger after insert on sell_order
for each row
BEGIN
	update customer
    set customer.total_spend = customer.total_spend + NEW.final_cost
    where customer.customer_id = NEW.customer_id;
END; 
//