//CREATE SCHEMA students;
/*
CREATE TABLE student_info(
    student_id int (20) NOT NULL PRIMARY KEY ,
    student_name varchar(40),
    student_age int,
    student_class varchar(30),
    PRIMARY KEY (student_id)
);

INSERT INTO student_info(student_id, student_name, student_age, student_class)
VALUES(935271294, 'Patrick', 282, 'D20B'); */
INSERT INTO students (name)
VALUES ( 'abe' ),
                  ('kat'),
                  ('hund'),
                  ('giraf');


INSERT INTO assignments (name, deadline, student_id)
VALUES ('Hent bananer', '2022-08-01', 1),
                  ('Kattemadssortering', '2025-01-25', 2),
                  ('Lig en dej', '2021-09-22', 3),
                  ('Se over bjerget', '2022-01-02', 4);







