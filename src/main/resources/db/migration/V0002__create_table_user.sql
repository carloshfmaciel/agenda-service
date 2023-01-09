CREATE TABLE users (
	id uuid NOT NULL,
	username varchar NOT NULL,
	cpf varchar NOT NULL,
	status varchar NOT NULL,
	last_modification_date TIMESTAMP NOT NULL,
	revision int NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);