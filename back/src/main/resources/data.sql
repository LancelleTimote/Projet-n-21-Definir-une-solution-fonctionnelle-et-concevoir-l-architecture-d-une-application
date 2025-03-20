INSERT INTO AGENCIES(name, address)
VALUES
('agency', 'address');

INSERT INTO USERS (firstname, lastname, email, password, birthdate, user_type)
VALUES
('firstname1', 'lastname1', 'email1@mail.com', 'password1', '1990-03-14', 'customer'),
('firstname2', 'lastname2', 'email2@mail.com', 'password2', '1985-06-21', 'customer'),
('firstname3', 'lastname3', 'email3@mail.com', 'password3', '1995-09-30', 'customer_service'),
('firstname4', 'lastname4', 'email4@mail.com', 'password4', '1988-12-11', 'customer_service');

INSERT INTO CUSTOMERS(user_id, address)
VALUES
(1,'address'),
(2,'address');


INSERT INTO CUSTOMER_SERVICE(user_id, agency_id)
VALUES
(3,1),
(4,1);
