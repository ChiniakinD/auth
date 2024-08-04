truncate table users cascade;
truncate table roles cascade;
truncate table user_roles cascade;

insert into users (login, password, email)
values (1, '$2b$12$z0CEM7LMMfdUpG2wCSDZT.KVrZpid3yyeu5B1PgXmBR5GJT6KhcsK', 'user@test.com'),
       (2, '$2a$12$gRXJq1AJdmF2zX7l1h98Ce6HP8NyIxdp3Yix.bMgpic1qep9jEWwK', 'admin@test.com');

insert into roles (role)
values ('USER'),
       ('CREDIT_USER'),
       ('OVERDRAFT_USER'),
       ('DEAL_SUPERUSER'),
       ('CONTRACTOR_RUS'),
       ('CONTRACTOR_SUPERUSER'),
       ('SUPERUSER'),
       ('ADMIN');

