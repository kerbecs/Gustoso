CREATE VIEW username_role AS
SELECT username, role_name FROM
    (SELECT role_id,user_id,username,name AS role_name FROM user_role
                                                                INNER JOIN
                                                            user ON user_role.user_id = user.id
                                                                INNER JOIN
                                                            role ON user_role.role_id = role.id) AS username_role;