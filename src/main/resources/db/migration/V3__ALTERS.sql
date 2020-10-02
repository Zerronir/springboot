ALTER TABLE person ADD COLUMN dni char(9) not null;
ALTER TABLE animal DROP COLUMN owner;
ALTER TABLE animal ADD COLUMN owner char(9) not null;