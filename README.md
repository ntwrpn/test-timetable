# test-timetable
Test timetable
Requirements: 
 - glassfish4.0
 - postgres11
 - openjdk11

Installation:
 - install PostgreSQL with your credentials
 - initialize database
```
CREATE DATABASE users;
CREATE USER root_user WITH PASSWORD 'password' WITH ALL PRIVILEGES;
INSERT INTO public.users(id, enabled, fullname, password, username) VALUES ('a4ad220c-e84e-4256-8adc-e1523aa659e1', true, '', '$2a$10$mppSXDR0IK9byV1OHq1Kdu7/ycd0bdoaeuTeEMry7fpxdOY87ii8q', 'root');
INSERT INTO public.user_roles(id, role) VALUES ('c35ebbca-8f74-457d-8a0d-d40c43a0ddf9', 'ROLE_ADMIN');
INSERT INTO public.user_roles(id, role) VALUES ('94c2b406-44d1-417b-be98-a8249d2f86b5', 'ROLE_USER');
INSERT INTO public.users_roles(users, user_role_id) VALUES ('a4ad220c-e84e-4256-8adc-e1523aa659e1', 'c35ebbca-8f74-457d-8a0d-d40c43a0ddf9');
```
