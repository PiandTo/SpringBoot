-- UPDATE SpringBoot.users SET is_admin = true WHERE user_id = '4';
-- UPDATE SpringBoot.users SET is_admin = false WHERE user_id = '1';
-- UPDATE SpringBoot.users SET is_admin = false WHERE user_id = '2';
-- UPDATE SpringBoot.users SET is_admin = false WHERE user_id = '3';
-- UPDATE SpringBoot.users SET is_admin = false WHERE user_id = '5';
-- UPDATE SpringBoot.users SET is_admin = false WHERE user_id = '6';
insert into springboot.users (first_name, last_name, email, phone_number, password, role, status, avatar, is_non_locked, is_non_enable, fail_attempts)
VALUES ('b', 'last_name', 'ADMIN', 'phone_number', '$2a$10$BDRuCkP91uTk2KfREKCSDuzrbZN/xRbchODFdqTlKNWSjQMSUKXHq', 'ADMIN', 'CONFIRMED', null, 'true', 'true', 0);

insert into springboot.authority (authorities_id, name) VALUES ('1', 'USER');
insert into springboot.users_authorities (user_id, authorities_id) VALUES ('35', '1');

insert into springboot.users (first_name, last_name, email, phone_number, password, role, status, avatar, is_non_locked, is_non_enable, fail_attempts)
VALUES ('b', 'last_name', 'ADMIN', 'phone_number', '$2a$10$BDRuCkP91uTk2KfREKCSDuzrbZN/xRbchODFdqTlKNWSjQMSUKXHq', 'ADMIN', 'CONFIRMED', null, 'true', 'true', 0);

update springboot.users
set role='ADMIN' where user_id='187'