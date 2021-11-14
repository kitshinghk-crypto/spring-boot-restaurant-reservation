CREATE SEQUENCE reservation_sequence start 1 increment 1;

CREATE TABLE reservation (
	RESERVATION_ID SERIAL PRIMARY KEY NOT NULL,
	RESTAURANT_ID INT NOT NULL,
	NUM_OF_PEOPLE INT NOT NULL,
	RESERVATION_DATETIME TIMESTAMP NOT NULL,
	CUSTOMER_NAME TEXT NOT NULL,
	PHONE_NUMBER TEXT NOT NULL,
	CREATE_DATETIME TIMESTAMP NOT NULL
);

CREATE TABLE restaurant(
	RESTAURANT_ID INT PRIMARY KEY NOT NULL,
	NAME TEXT
);

INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (1, 'RAMEN BAR');
INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (2, 'SHUSHI RESTAURANT');
INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (3, 'Blacksmith');
INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (4, 'Island Creamery');
INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (5, 'Baked Dessert Cafe');
INSERT INTO restaurant (RESTAURANT_ID, NAME) VALUES (6, 'Rayne s Reef Soda Fountain & Grill');