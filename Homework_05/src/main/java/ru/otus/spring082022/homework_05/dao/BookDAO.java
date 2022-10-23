package ru.otus.spring082022.homework_05.dao;

import ru.otus.spring082022.homework_05.domain.Book;

import java.util.List;

public interface BookDAO {

    int count();

    long insert(Book book);

    Book getById(long id);

    void update(Book book);

    List<Book> getAll();

    void deleteById(long id);


}
