ALTER TABLE vote
    DROP COLUMN yes_answer, no_answer;

ALTER TABLE vote ADD(
	answer varchar NULL
);