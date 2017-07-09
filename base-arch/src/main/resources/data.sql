INSERT INTO security.users (USERNAME, PASSWORD, ENABLED, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED) VALUES
	('krishna', '$2a$10$bwG9f.T16qMfnegO.1CX0.hflrX3mrnK0n9saWipFppbyXu8O26Lm', true, true, true),
	('sudama', '$2a$10$bwG9f.T16qMfnegO.1CX0.hflrX3mrnK0n9saWipFppbyXu8O26Lm', true, true, true),
	('superuser', '$2a$10$bwG9f.T16qMfnegO.1CX0.hflrX3mrnK0n9saWipFppbyXu8O26Lm', true, true, true); 

INSERT INTO security.user_authorities (ID, USERNAME, AUTHORITY) VALUES
	('1', 'krishna', 'ADMIN_ROLE'),
	('2', 'sudama', 'BASIC_ROLE'),
	('3', 'krishna', 'BASIC_ROLE');
	('4', 'superuser', 'APP_ROLE');	