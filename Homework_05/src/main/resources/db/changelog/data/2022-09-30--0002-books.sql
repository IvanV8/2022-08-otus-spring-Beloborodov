--liquibase formatted sql
--changeset IvanBeloborodov:2022-09-30--0002-books.sql

insert into authors (id, name)
values (1, 'Alexander Pushkin'),
       (2, 'Michail Lermontov');

insert into genres (id, name)
values (1, 'Roman'),
       (2, 'Poem');

insert into books (title, isbn, author_id, genre_id)
values ('Evgeny Onegin', '978-5-04-116656-4', 1, 2),
       ('The Hero of our Time', '978-5-04-096656-1', 2, 1);
