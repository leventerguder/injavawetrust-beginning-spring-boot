delete from  user_role;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER')
;

-- password : admin
-- password : guess
-- password : employee
-- You can use BCryptPasswordEncoder#encode method for encoded password.

INSERT INTO users (id, email, password, name) VALUES 
(1, 'admin@gmail.com', '$2a$10$6SZSdwZl38CF3I8wPWgSm.IMofpdwsWIRfTfCsZnFSaUefYItBksW', 'Admin'),
(2, 'guest@gmail.com', '$2a$10$FLtNpxxv2REwNJ7.Vv.KIe7o08zACltK7VrWRUAFX/o8t3T9xgSTu', 'Guest'),
(3, 'employee@gmail.com', '$2a$10$jooS6nG9jokgjgYmSAw5Zu7Cvdn3YFLRLJ0cWpDkzYvwCiXEeePGG', 'Employee');


insert into user_role(user_id, role_id) values
(1,1),
(1,2),
(3,2)
;

insert into posts(post_id, title, content, created_on, updated_on) values
(1,'Introducing SpringBoot','SpringBoot is an opinionated approach for building Spring applications.', '2017-06-20', null),
(2,'CRUD using Spring Data JPA','Spring Data JPA provides JpaRepository which can be extended to have CRUD operations', '2017-06-25', null),
(3,'Securing Web apps using SpringSecurity','Spring Security provides Authentication and Authorization support.', '2017-04-20', now())
;