-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, drug, class CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE class (
    class_id SERIAL PRIMARY KEY,
    class_name varchar (50) NOT NULL
);
CREATE TABLE drug (
    drug_id SERIAL PRIMARY KEY,
	class_id int,
   	drug_name varchar (50) UNIQUE NOT NULL,
   	cost decimal (10,2) NOT NULL,
   	is_available boolean DEFAULT TRUE,
   	FOREIGN KEY (class_id) REFERENCES class(class_id)
);


-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

INSERT INTO
    class (class_name)
VALUES
    ('ACE Inhibitors'),
    ('Antihistamine'),
    ('Diuretics'),
    ('Statins');

INSERT INTO
    drug (drug_name, cost, is_available, class_id)
VALUES
    ('enalipril', 15.99, TRUE, 1),
    ('captopril', 14.27, FALSE, 1),
    ('lisinopril', 9.47, TRUE, 1),
    ('diphenhydramine', 4.98, TRUE, 2),
    ('cetirizine', 5.99, TRUE, 2),
    ('hydroxyzine', 19.43, TRUE, 2),
    ('furosemide', 13.34, FALSE, 3),
    ('hydrochlorothiazide', 18.75, TRUE, 3),
    ('atorvastatin', 5.47, TRUE, 4),
    ('rosuvastatin', 9.47, TRUE, 4);


COMMIT TRANSACTION;
