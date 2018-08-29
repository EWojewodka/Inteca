BEGIN;

CREATE TABLE inteca_families(
	family_id		SERIAL PRIMARY KEY,
	father_id		INTEGER NOT NULL UNIQUE
);

CREATE TABLE inteca_fathers(
	father_id 		SERIAL PRIMARY KEY,
	first_name		VARCHAR(255) NOT NULL,
	second_name		VARCHAR(255),
	pesel			VARCHAR(255),
	birth_date		DATE NOT NULL,
	family_id		INTEGER NOT NULL
);

CREATE TABLE inteca_children(
	child_id 		SERIAL PRIMARY KEY,
	first_name		VARCHAR(255) NOT NULL,
	second_name		VARCHAR(255),
	pesel			VARCHAR(255),
	sex			VARCHAR(100),
	family_id		INTEGER NOT NULL
);

ALTER TABLE inteca_fathers ADD CONSTRAINT inteca_father2family FOREIGN KEY (father_id) REFERENCES inteca_families(father_id);
ALTER TABLE inteca_children ADD CONSTRAINT inteca_child2family FOREIGN KEY (family_id) REFERENCES inteca_families(family_id);


COMMIT;