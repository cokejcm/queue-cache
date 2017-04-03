CREATE SCHEMA IF NOT EXISTS security;

CREATE TABLE IF NOT EXISTS security.users (
  USERNAME TEXT NOT NULL,
  PASSWORD TEXT NOT NULL,
  ENABLED boolean NOT NULL,
  ACCOUNT_NON_EXPIRED boolean,
  ACCOUNT_NON_LOCKED  boolean, 
  PRIMARY KEY (username));

INSERT INTO security.users (USERNAME, PASSWORD, ENABLED, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED) VALUES
	('krishna', '$2a$10$bwG9f.T16qMfnegO.1CX0.hflrX3mrnK0n9saWipFppbyXu8O26Lm', true, true, true),
	('sudama', '$2a$10$bwG9f.T16qMfnegO.1CX0.hflrX3mrnK0n9saWipFppbyXu8O26Lm', true, true, true); 
	
CREATE TABLE IF NOT EXISTS security.user_authorities (
  ID TEXT NOT NULL,
  USERNAME TEXT NOT NULL,
  AUTHORITY TEXT NOT NULL,
  UNIQUE (USERNAME, AUTHORITY),
  CONSTRAINT fk_authorities_users FOREIGN KEY (USERNAME) REFERENCES security.users (USERNAME));

INSERT INTO security.user_authorities (ID, USERNAME, AUTHORITY) VALUES
	('1', 'krishna', 'ROLE_ADMIN'),
	('2', 'sudama', 'ROLE_USER');