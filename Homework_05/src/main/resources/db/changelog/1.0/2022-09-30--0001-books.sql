--liquibase formatted sql
--changeset IvanBeloborodov:2022-09-30--0001-books.sql

CREATE TABLE GENRES
(
    ID   BIGINT auto_increment PRIMARY KEY,
    NAME VARCHAR(255),
    CONSTRAINT genres_pk PRIMARY KEY (ID)
);
CREATE TABLE AUTHORS
(
    ID   BIGINT auto_increment PRIMARY KEY,
    NAME VARCHAR(255),
    CONSTRAINT authors_pk PRIMARY KEY (ID)
);

CREATE TABLE BOOKS
(
    ID        BIGINT auto_increment PRIMARY KEY,
    TITLE     VARCHAR(255),
    ISBN      VARCHAR(255),
    AUTHOR_ID BIGINT NOT NULL,
    GENRE_ID  BIGINT NOT NULL,
    CONSTRAINT book_pk PRIMARY KEY (ID),
    CONSTRAINT books_author_id FOREIGN KEY (AUTHOR_ID)
        REFERENCES AUTHORS (ID),
    CONSTRAINT books_genre_id FOREIGN KEY (GENRE_ID)
        REFERENCES GENRES (ID)
);
