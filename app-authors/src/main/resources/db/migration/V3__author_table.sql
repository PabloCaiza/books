create table if not exists authors
(
    id
    serial
    primary
    key,
    first_name
    varchar
(
    255
),last_name varchar
(
    255
));

insert into authors(first_name, last_name)
values ('author1', 'a1');
insert into authors(first_name, last_name)
values ('author2', 'a2');
insert into authors(first_name, last_name)
values ('author3', 'a3');
insert into authors(first_name, last_name)
values ('author4', 'a4');