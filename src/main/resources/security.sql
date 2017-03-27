CREATE SCHEMA IF NOT EXISTS security;

CREATE TABLE IF NOT EXISTS security.users (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (username));

INSERT INTO security.users (username, password, enabled) VALUES
	('krishna', '21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5', true),
	('sudama', '904752ad9c4ae4186c4b4897321c517de0618702', true); 
	
CREATE TABLE IF NOT EXISTS security.user_authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE (username,authority),
  CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES security.users (username));

INSERT INTO security.user_authorities (username, authority) VALUES
	('krishna', 'ROLE_ADMIN'),
	('sudama', 'ROLE_USER');