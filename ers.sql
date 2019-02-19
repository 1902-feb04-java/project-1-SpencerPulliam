-- CREATE DATABASE employee_reimbursment_system;

--  CREATE SCHEMA reimbursments;

ALTER TABLE employees 
    MODIFY id SERIAL,
    MODIFY first_name VARCHAR(200) NOT NULL,
    MODIFY last_name VARCHAR(200) NOT NULL,
    MODIFY title VARCHAR(200) NOT NULL,
    MODIFY email VARCHAR(200) NOT NULL,
    MODIFY password VARCHAR(200) NOT NULL,
    MODIFY is_manager BOOL NOT NULL,
    MODIFY routing_no BIGINT NOT NULL,
    MODIFY acct_no BIGINT NOT NULL,
    MODIFY manager_id INT NOT NULL
)

ALTER TABLE reimbursment_requests (
    MODIFY req_id SERIAL NOT NULL,
    MODIFY request_date DATE NOT NULL,
    MODIFY approval_date DATE,
    MODIFY emp_id INT NOT NULL FOREIGN KEY,
    MODIFY approving_manager_id INT NOT NULL,
    MODIFY status VARCHAR(200) NOT NULL
        CHECK (status = 'pending' OR 'denied' OR 'approved'),
    MODIFY description VARCHAR(200),
    MODIFY documentation VARCHAR(500), 
    MODIFY total DECIMAL(5,2)
    
);
