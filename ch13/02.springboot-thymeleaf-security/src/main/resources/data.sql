
create table if not exists persistent_logins ( 
  username varchar(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);

delete from  user_role;
delete from  roles;
delete from  users;


INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_ACTUATOR'),
(3, 'ROLE_USER')
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
(1,3),
(3,2)
;