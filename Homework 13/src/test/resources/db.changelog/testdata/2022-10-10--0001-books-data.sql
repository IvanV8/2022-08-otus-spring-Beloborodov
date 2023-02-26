insert into roles (name)
values ('ADMIN'),
       ('USER'),
       ('CHILD');
commit;

insert into users (login, password, firstname, lastname, role_id)
values ('AIvanov', 'password', 'Arsen', 'Ivanov', 1),
       ('DSergeev', 'password', 'Dmitriy', 'Sergeev', 2),
       ('Gosha', 'password', 'Gosha', 'Alekseev', 3);;
commit;
insert into authors (name)
values ('Alexander Pushkin'),
       ('Michail Lermontov');
commit;
insert into genres (name)
values ('Roman'),
       ('Poem');
commit;
insert into books (title, isbn, author_id, genre_id)
values ('Evgeny Onegin', '978-5-04-116656-4', 1, 2),
       ('The Hero of our Time', '978-5-04-096656-1', 2, 1);
commit;
insert into comments (user_id, datetime, text, book_id)
values (1, '21-09-14 12:00', 'Great book!', 1);