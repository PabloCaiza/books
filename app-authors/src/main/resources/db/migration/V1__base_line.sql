create table if not exists books
(
    id serial
    primary key,
    isbn   varchar(255),
    title  varchar(255),
    author varchar(255),
    price  numeric(9, 2)
    );

alter table books
    owner to postgres;