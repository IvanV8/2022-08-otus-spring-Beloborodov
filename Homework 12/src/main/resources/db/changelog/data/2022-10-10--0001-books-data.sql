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
insert into comments (user_name, datetime, text, book_id)
values ('Alexey Yablokov', '21-09-14 12:00', 'Great book!', 1);
insert into users (login, password, firstname, lastname)
values ('AIvanov', 'qwerty123', 'Arsen', 'Ivanov'),
       ('DSergeev', 'digital123', 'Dmitriy', 'Sergeev');
commit;