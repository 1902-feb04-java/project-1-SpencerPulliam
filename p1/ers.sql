/*
CREATE TABLE employees(
	id SERIAL PRIMARY KEY,
	title VARCHAR(100),
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	reports_to INTEGER REFERENCES employees(id)
);
*/

CREATE TABLE requests(
	id SERIAL PRIMARY KEY,
	amount DECIMAL(8,2) NOT NULL,
	employee_id INTEGER REFERENCES employees(id) NOT NULL,
	status VARCHAR(50) DEFAULT 'PENDING',
	image BYTEA,
	date DATE,
	description VARCHAR(100)
);

/*


CREATE TABLE credentials (
    user_id INTEGER REFERENCES employees(id) NOT NULL,
    username VARCHAR(100),
    password VARCHAR(100)
);



INSERT INTO credentials VALUES
('1', 'goku', 'dbz');
    
    

INSERT INTO employees (title, first_name, last_name) VALUES 
('Owner', 'Akira', 'Toriyama');
INSERT INTO employees (title, first_name, last_name, reports_to) VALUES        ('Marketing Director', 'Jason', 'Voorhees', 1);
INSERT INTO employees (title, first_name, last_name, reports_to) VALUES ('Technology Director', 'Linus', 'Torvalds', 1);
INSERT INTO employees (title, first_name, last_name, reports_to) VALUES ('Death Star Technician', 'Melody', 'Pulliam', 3);
INSERT INTO employees (title, first_name, last_name, reports_to) VALUES ('Fiancial Planner', 'Jordan', 'Belfort', 2);

INSERT INTO requests (amount, employee_id, status) VALUES (3.50, 4, 'PENDING');
*/



