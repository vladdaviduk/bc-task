use university_base;

drop table if exists department_lector;
drop table if exists lector;
drop table if exists department;

create table if not exists lector (
    id int not null auto_increment,
    name varchar(255) not null,
    degree varchar(255) not null,
    primary key (id)
);

create table if not exists department (
    id int not null auto_increment,
    name varchar(255) not null,
    head varchar(255) not null,
    primary key (id)
);

create table if not exists department_lector (
    department_id int not null,
    lector_id int not null,
    salary decimal(15,2) not null,
    foreign key (department_id) REFERENCES department(id),
    foreign key (lector_id) REFERENCES lector(id),
    primary key (department_id, lector_id)
);

insert into department values (1, 'Engineering', 'Cynthia Sutton');
insert into department values (2, 'Psychology', 'Ellen Parker');
insert into department values (3, 'History', 'Bentley Schneider');
insert into department values (4, 'Law', 'Eva Hart');
insert into department values (5, 'Biochemistry', 'Ebenezer Mitchell'); #without lectors

insert into lector values (1, 'Rowan Soto', 'ASSOCIATE_PROFESSOR');
insert into lector values (2, 'Phoebe Chambers', 'ASSOCIATE_PROFESSOR');
insert into lector values (3, 'Lawrence Titterington', 'PROFESSOR');
insert into lector values (4, 'Harold Thompson', 'PROFESSOR');
insert into lector values (5, 'Teri Baxter', 'PROFESSOR'); #without departments
insert into lector values (6, 'Ed Erickson', 'ASSISTANT');
insert into lector values (7, 'Freddie Slater', 'ASSISTANT');
insert into lector values (8, 'Esmond Marsh', 'ASSISTANT');
insert into lector values (9, 'Vita Barber', 'ASSISTANT');

insert into department_lector (department_id, lector_id, salary) values (1, 1, 1700);
insert into department_lector (department_id, lector_id, salary) values (2, 2, 2000);
insert into department_lector (department_id, lector_id, salary) values (3, 3, 3500);
insert into department_lector (department_id, lector_id, salary) values (4, 4, 3700);
insert into department_lector (department_id, lector_id, salary) values (3, 6, 700.50);
insert into department_lector (department_id, lector_id, salary) values (4, 6, 500);
insert into department_lector (department_id, lector_id, salary) values (1, 7, 900);
insert into department_lector (department_id, lector_id, salary) values (2, 8, 900);
insert into department_lector (department_id, lector_id, salary) values (3, 9, 700);


