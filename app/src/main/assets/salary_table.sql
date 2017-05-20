BEGIN TRANSACTION;
CREATE TABLE `salary_table` (
	`company_name`	TEXT NOT NULL UNIQUE,
	`salary`	INTEGER NOT NULL,
	PRIMARY KEY(`company_name`)
);
INSERT INTO `salary_table` VALUES ('Infosys',514113);
INSERT INTO `salary_table` VALUES ('Hcl Technologies',419800);
COMMIT;
