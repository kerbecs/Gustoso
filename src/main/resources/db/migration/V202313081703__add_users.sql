INSERT INTO restaurant.user_description(firstName, lastName, email, city, address)
VALUES('Mititiuc','Eduard','eduard@gmail.com','Singera','Alecu Russo 2A');

INSERT INTO restaurant.user(username, password, is_active,user_description)
VALUES('eduard.mititiuc','{bcrypt}$2y$10$NEzoUhZteMWqFCLoR0LTcOGpChnmMZO.s6LsvHg1PBDZeNwHKlPcu',1,1);

INSERT INTO restaurant.user_role VALUES(1,1),(1,2);