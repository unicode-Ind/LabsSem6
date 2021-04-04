CREATE TABLE emp_table(
		           emp_id int,
		           e_name varchar(20),
		           email varchar(20),
		           birthday date,
		           position varchar(20),
		           dept_id int,
		           salary int DEFAULT 10000
		           );

-- ALTER TABLE emp_table
-- 	ADD salary int DEFAULT 10000

ALTER TABLE emp_table
	MODIFY (emp_id int NOT NULL,
		    e_name varchar(20) NOT NULL,
			dept_id int NOT NULL);

ALTER TABLE emp_table
	ADD PRIMARY KEY(emp_id)

INSERT INTO emp_table (emp_id,e_name,email,birthday,position,dept_id,salary)
		VALUES
			(1,'A','a@gmail.com',to_date('1976-05-02','YYYY-MM-DD'),'SDE1',1,75000),
			(2,'B','b@gmail.com',to_date('1977-04-09','YYYY-MM-DD'),'SDE2',1,90000),
			(3,'C','c@gmail.com',to_date('1974-07-08','YYYY-MM-DD'),'SDE1',2,75000),
			(4,'D','d@gmail.com',to_date('1973-08-12','YYYY-MM-DD'),'SDE2',2,90000),
			(5,'E','e@gmail.com',to_date('1976-05-02','YYYY-MM-DD'),'SDE1',3,75000),
			(6,'F','f@gmail.com',to_date('1977-04-09','YYYY-MM-DD'),'SDE2',3,90000),
			(7,'G','g@gmail.com',to_date('1974-07-08','YYYY-MM-DD'),'SDE1',4,75000),
			(8,'H','h@gmail.com',to_date('1973-08-12','YYYY-MM-DD'),'SDE2',4,90000),
			(9,'I','i@gmail.com',to_date('1976-05-02','YYYY-MM-DD'),'SDE1',5,75000),
			(10,'J','j@gmail.com',to_date('1977-04-09','YYYY-MM-DD'),'SDE2',5,90000),
			(11,'K','k@gmail.com',to_date('1974-07-08','YYYY-MM-DD'),'SDE1',6,75000),
			(12,'L','l@gmail.com',to_date('1973-08-12','YYYY-MM-DD'),'SDE2',6,90000),
			(13,'M','m@gmail.com',to_date('1976-05-02','YYYY-MM-DD'),'SDE1',7,75000),
			(14,'N','n@gmail.com',to_date('1977-04-09','YYYY-MM-DD'),'SDE2',7,90000),
			(15,'O','o@gmail.com',to_date('1974-07-08','YYYY-MM-DD'),'SDE1',8,75000),
			(16,'P','p@gmail.com',to_date('1973-08-12','YYYY-MM-DD'),'SDE2',8,90000),
			(17,'Q','q@gmail.com',to_date('1976-05-02','YYYY-MM-DD'),'SDE1',9,75000),
			(18,'R','r@gmail.com',to_date('1977-04-09','YYYY-MM-DD'),'SDE2',9,90000),
			(19,'S','s@gmail.com',to_date('1974-07-08','YYYY-MM-DD'),'SDE1',10,75000),
			(20,'T','t@gmail.com',to_date('1973-08-12','YYYY-MM-DD'),'SDE2',10,90000);


SELECT *from emp_table 
	ORDER BY emp_id;


CREATE TABLE dept_table (dept_id INT, d_name Varchar(30), loc Varchar(20))
			
INSERT INTO dept_table (dept_id, d_name,loc) VALUES
(1,'Dep1','DELHI'),
(2,'Dep2','MUMBAI'),
(3,'Dep3','DELHI'),
(4,'Dep4','MUMBAI'),
(5,'Dep5','MUMBAI'),
(6,'Dep6','DELHI'),
(7,'Dep7','BANGALORE'),
(8,'Dep8','DELHI'),
(9,'Dep9','BANGALORE'),
(10,'Dep10','DELHI');

SELECT *FROM emp_table JOIN dept_table
		ON emp_table.dept_id = dept_table.dept_id
		ORDER BY emp_id;

DELETE FROM emp_table where emp_id=16

alter table "DEPT_TABLE" add
("HIKE" NUMBER NULL)

UPDATE dept_table
		SET HIKE = 1.5
		where dept_id > 5

UPDATE dept_table
		SET HIKE = 1.2
		where dept_id <= 5

SELECT emp_table.emp_id,
	   emp_table.e_name,
	   emp_table.salary * dept_table.hike AS "NET SALARY"
FROM emp_table JOIN dept_table 	ON emp_table.dept_id = dept_table.dept_id
ORDER BY emp_id


SELECT emp_table.emp_id,
	   emp_table.e_name,
	   emp_table.salary * dept_table.hike AS "NET SALARY"
FROM emp_table JOIN dept_table 	ON emp_table.dept_id = dept_table.dept_id
ORDER BY "NET SALARY" DESC

SELECT *from dept_table
    ORDER BY loc 