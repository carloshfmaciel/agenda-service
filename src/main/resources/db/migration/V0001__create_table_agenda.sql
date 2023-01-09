CREATE TABLE agenda (
	id uuid NOT NULL,
	question varchar NOT NULL,
	start_vote TIMESTAMP NULL,
	end_vote TIMESTAMP NULL,
	status varchar NOT NULL,
	last_modification_date TIMESTAMP NOT NULL,
	revision int4 NULL,
	CONSTRAINT agenda_pk PRIMARY KEY (id)
);