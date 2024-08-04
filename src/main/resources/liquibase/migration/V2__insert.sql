--liquibase formatted sql

--changeset ChiniakinD:4
--comment: insert table roles
insert into roles (role)
values ('USER'),
       ('CREDIT_USER'),
       ('OVERDRAFT_USER'),
       ('DEAL_SUPERUSER'),
       ('CONTRACTOR_RUS'),
       ('CONTRACTOR_SUPERUSER'),
       ('SUPERUSER'),
       ('ADMIN');