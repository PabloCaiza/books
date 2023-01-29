DELETE
FROM books;

ALTER TABLE books
DROP
COLUMN  author,
    ADD author_id INTEGER,
    ADD FOREIGN KEY (author_id) REFERENCES authors(id);

insert into books(isbn, title, author_id, price)
values ('11-11', 'title1', 1, 20),
       ('22-22', 'title2', 2, 20),
       ('33-33', 'title3', 3, 20),
       ('44-44', 'title4', 4, 20);
