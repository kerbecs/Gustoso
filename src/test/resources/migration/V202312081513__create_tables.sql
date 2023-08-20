

DROP TABLE IF EXISTS user_description;
CREATE TABLE user_description
(
    id        int8 PRIMARY KEY AUTO_INCREMENT,
    firstName varchar(25) NOT NULL CHECK (firstName REGEXP '^([A-Za-z]{2,25})$'
) ,
lastName varchar(25) NOT NULL CHECK(lastName REGEXP   '^([A-Za-z]{2,25})$'),
email varchar(70) NOT NULL UNIQUE,
city varchar(25) NOT NULL CHECK(city REGEXP  '^([A-Za-z\- \']{2,25})$'),
address varchar(70) NOT NULL,
user_description varchar(100),
job varchar(30) CHECK(job REGEXP  '^([A-Za-z\-_ \']{0,20})$'),
orders int NOT NULL CHECK(orders >= 0) DEFAULT 0
);


DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       int8 PRIMARY KEY AUTO_INCREMENT,
    username varchar(50) NOT NULL UNIQUE CHECK (username REGEXP '^([a-zA-Z_0-9\-_\.]{5,68})$'
) ,
                     password varchar(68) NOT NULL,
                     is_active tinyint(1) NOT NULL DEFAULT 0,
                     user_description int8 NOT NULL UNIQUE,
                     CONSTRAINT user_description_fk FOREIGN KEY(user_description) REFERENCES user(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    id   int8 PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) NOT NULL UNIQUE CHECK (name REGEXP '^([A-Za-z_]{2,20})$'
)
    );

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    user_id int8,
    role_id int8,
    CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_role_role_fk FOREIGN KEY (role_id) REFERENCES role (id)
);

DROP TABLE IF EXISTS produce;
CREATE TABLE produce
(
    id   int8 PRIMARY KEY AUTO_INCREMENT,
    name varchar(40) NOT NULL UNIQUE,
    ingredients varchar(200) NOT NULL,
    weight int NOT NULL CHECK(weight > 0),
    price long NOT NULL CHECK(price > 0),
    image varchar(20)
    );

    DROP TABLE IF EXISTS orders;
    CREATE TABLE orders
    (
        id      int8 PRIMARY KEY AUTO_INCREMENT,
        user_id int8     NOT NULL,
        cost    long     NOT NULL CHECK (cost > 0),
        date    DATETIME NOT NULL,
        CONSTRAINT order_user_id FOREIGN KEY (user_id) REFERENCES user (id)
    );

    DROP TABLE IF EXISTS order_produce;
    CREATE TABLE order_produce
    (
        order_id   int8,
        produce_id int8,
        cost       long NOT NULL CHECK (cost > 0),
        CONSTRAINT order_produce_pk PRIMARY KEY (order_id, produce_id),
        CONSTRAINT order_produce_order_fk FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE ,
        CONSTRAINT order_produce_produce_fk FOREIGN KEY (produce_id) REFERENCES produce (id)
    );