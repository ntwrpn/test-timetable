# test-timetable
Test timetable
Requirements: 
 - glassfish4.0
 - postgres11
 - openjdk1.8

Installation:
 - install PostgreSQL with your credentials
 - initialize database
 - - CREATE DATABASE users;
 - - CREATE USER root_user WITH PASSWORD 'password' WITH ALL PRIVILEGES;
 - - INSERT INTO public.users(enabled, fullname, password, username) VALUES (true, '', '$2a$10$mppSXDR0IK9byV1OHq1Kdu7/ycd0bdoaeuTeEMry7fpxdOY87ii8q', 'root'); #password:password username:root
 - - INSERT INTO public.user_roles(user_role_id, role) VALUES (1, 'ROLE_ADMIN');
 - - INSERT INTO public.user_roles(user_role_id, role) VALUES (2, 'ROLE_USER');
 - - INSERT INTO public.users_roles(users, user_role_id) VALUES (1, 1);

