
CREATE TABLE token(
                      id int PRIMARY KEY AUTO_INCREMENT,
                      confirmation_token varchar(36) NOT NULL UNIQUE,
                      expire_date datetime NOT NULL,
                      user_id int NOT NULL REFERENCES user(id)
);