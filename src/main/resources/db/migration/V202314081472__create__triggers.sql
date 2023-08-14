USE restaurant;

delimiter |
CREATE TRIGGER update_user_order
    BEFORE INSERT
    ON orders
    FOR EACH ROW
BEGIN
    UPDATE user_description SET orders = orders + 1 WHERE id = new.user_id;
END
    |
delimiter ;

