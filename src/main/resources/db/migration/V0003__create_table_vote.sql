CREATE TABLE vote (
	id uuid NOT NULL,
	id_user uuid NOT NULL,
	id_agenda uuid NOT NULL,
	last_modification_date TIMESTAMP NOT NULL,
	CONSTRAINT vote_pk PRIMARY KEY (id),
	CONSTRAINT vote_user_fk FOREIGN KEY (id_user) REFERENCES users(id),
	CONSTRAINT vote_agenda_fk FOREIGN KEY (id_agenda) REFERENCES agenda(id),
	CONSTRAINT vote_un UNIQUE (id_user, id_agenda)
);