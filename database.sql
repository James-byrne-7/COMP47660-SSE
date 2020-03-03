create database springfield;

use springfield;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `role` varchar(40) NOT NULL DEFAULT 'Student',
  `sex` varchar(40) NOT NULL,
  `fees` int(11) NOT NULL DEFAULT '0',
  `firstname` varchar(40) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `nationality` varchar(255) NOT NULL DEFAULT 'Unknown',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);

CREATE TABLE `modules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `topics` varchar(40) DEFAULT NULL,
  `is_finished` char(1) NOT NULL DEFAULT 'N',
  `coordinator_id` int(11) DEFAULT '9999999',
  `max_students` int(11) NOT NULL DEFAULT '30',
  PRIMARY KEY (`id`),
  KEY `coordinator_id` (`coordinator_id`),
  CONSTRAINT `modules_ibfk_1`
  	FOREIGN KEY (`coordinator_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `involvement` (
  `user_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `grade` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`user_id`,`module_id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `involvement_ibfk_2`
  	FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`),
  CONSTRAINT `involvement_ibfk_3`
  	FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

-- Set up some students
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student1', 'student1', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student2', 'student2', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student3', 'student3', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student4', 'student4', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student5', 'student5', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student6', 'student6', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student7', 'student7', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student8', 'student8', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student9', 'student9', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student10', 'student10', 'M', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student11', 'student11', 'F', 'Irish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student12', 'student12', 'F', 'Indian');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student13', 'student13', 'F', 'Indian');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student14', 'student14', 'F', 'Indian');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student15', 'student15', 'F', 'Indian');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student16', 'student16', 'M', 'Indian');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student17', 'student17', 'M', 'Spanish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student18', 'student18', 'M', 'Spanish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student19', 'student19', 'M', 'Spanish');
INSERT INTO users(username, password, sex, nationality)
	VALUES ('student20', 'student20', 'M', 'Spanish');



-- Set up some staff
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300001, "Liliana","staff","Liliana","Pasquale", 'F', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300002, "Felix","staff","Felix","Balado", 'M', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300003, "Michela","staff","Michela","Bertolotto", 'F', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300004, "Chris","staff","Chris","Bleakley", 'M', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300005, "Rem","staff","Rem","Collier",'M', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300006, "John_D","staff","John", "Dunnion",'M', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300007, "Georgiana","staff","Georgiana","Ifrim",'F', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300008, "Alexey","staff","Alexey", "Lastovetsky",'M', 'Staff', 'Dutch');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300009, "Mark_s","staff","Mark", "Scanlon",'M', 'Staff','Polish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300010, "Henry","staff","Henry","McLoughlin", 'M', 'Staff','Polish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300011, "Aonghus","staff","Aonghus","Lawlor", 'M', 'Staff','Polish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300012, "Mark_k","staff","Mark", "Keane",'M', 'Staff','Polish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300013, "Neil","staff","Neil", "Hurley", 'M', 'Staff','Polish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300014, "Eleni","staff","Eleni","Mangina",'F', 'Staff','Brittish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300015, "Tony","staff","Tony", "Veale",'M', 'Staff','Brittish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300016, "Guenole","staff","Guenole","Silvestre",'M', 'Staff','Brittish');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300017, "Mel","staff","Mel","O Cinneide", 'M', 'Staff', 'German');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300018, "Liam","staff","Liam", "Murphy", 'M', 'Staff', 'German');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300019, "Gavin","staff","Gavin","McArdle",'M', 'Staff', 'German');
INSERT INTO users(id,username, password, firstname, lastname, sex, role, nationality)
	VALUES (300020, "John_M","staff","John", "Murphy", 'M', 'Staff', 'German');

INSERT INTO users(id, nationality, username, password, sex, role)
	values (500000, 'sudo', 'admin', 'admin', 'F', 'Staff');
INSERT INTO users(id, nationality, username, password, sex, role)
	values (500001, 'sudo', 'root', 'root', 'M', 'Staff');


-- create some modules
INSERT INTO modules(name, topics, coordinator_id)
	values ("Security Software Engineering", "Software" ,300001);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Information Systems", "Coding Theory" ,300002);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Intro to Databases", "Databases" ,300003);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Software Engineering Project II", "Software" ,300004);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Distributed Systems", "Networking" ,300005);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Formal Foundations", "Logic" ,300006);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Compiler Design", "Grammars" ,300007);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Agents", "Agents" ,300008);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Forensic Computing", "Hacking" ,300009);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Program Construciton", "Math" ,300010);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Programming", "C++" ,300011);
INSERT INTO modules(name, topics, coordinator_id)
	values ("CS in Practice", "Dunno" ,300012);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Programming II", "N/A" ,300013);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Java", "Java" ,300014);
INSERT INTO modules(name, topics, coordinator_id)
	values ("FYP", "Projects" ,300015);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Computer Graphics", "Graphics" ,300016);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Ruby", "Ruby" ,300017);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Internet Systems", "Networking" ,300018);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Data Structures", "Data Structures" ,300019);
INSERT INTO modules(name, topics, coordinator_id)
	values ("Performance of Computers", "Performance monitoring" ,300020);

-- Enrol random students in random modules
Insert into involvement (user_id, module_id) values
(11,20),(4,7),(5,13),(5,2),(17,12),(6,17),
(12,14),(10,4),(16,6),(5,8),(19,20),(1,6),(9,18),(17,20),(15,11),
(7,12),(17,15),(9,16),(1,4),(10,7),(2,15),(18,8),(7,3),(10,16),(20,9),
(12,9),(2,8),(20,7),(1,14),(1,7),(17,14),(9,7),(18,17),(20,1),(6,19),
(4,8),(7,6),(6,6),(4,15),(18,3),(4,20),(8,1),(2,18),(6,12),(12,17),
(2,11),(13,12),(3,6),(8,12),(2,6),(18,11),(1,15),(8,19),(14,15),(5,6),
(20,11),(10,11),(5,1),(16,13),(5,14),(19,16),(16,16),(20,16),(2,13),
(12,15),(13,7),(16,7),(15,5),(20,3),(5,9),(19,13),(5,16),(3,14),
(4,5),(6,4),(10,5),(12,12),(1,18),(9,15),(4,4),(10,10),(17,17),(5,11),
(9,19),(9,10),(18,5),(4,17),(2,1),(1,11),(14,20),(12,13),(6,1),(7,19),
(16,14),(9,11),(12,20),(9,5),(15,14),(7,1),(13,20),(4,14),(8,11),
(8,18),(20,18),(7,8),(3,5),(14,16),(4,6),(2,17),(3,1),(11,19),(3,3),
(10,2),(7,13),(15,1),(3,2),(15,13),(6,2),(10,6),(2,7),(15,9),(14,2),
(13,4),(15,12),(17,11),(10,8),(12,1),(3,16),(4,1),(7,14),(10,18),
(7,11),(14,18),(15,2),(3,20),(20,15),(18,12),(18,13),(12,11),(15,19),
(17,6),(14,19),(8,2),(8,5),(6,16),(13,18),(12,5),(10,13),(8,9),(20,19),
(11,6),(11,4),(11,8),(14,12),(13,6),(13,15),(3,19),(6,15),(4,2),(15,15),
(11,14),(7,5),(7,15),(5,17),(20,5),(2,9),(14,10),(14,14),(3,17),
(20,14),(16,9),(13,17),(18,6),(20,8),(7,10),(2,20),(17,5),(11,5),
(6,7),(3,18),(20,4),(10,9),(14,3),(17,18),(12,19),(17,10),
(11,13),(12,2),(8,6),(18,1),(10,15),(1,19),(8,14),(4,13),(8,7);











