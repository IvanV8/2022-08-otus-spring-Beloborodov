package ru.otus.spring082022.homework_05.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework_05.domain.Author;
import ru.otus.spring082022.homework_05.domain.Book;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOJdbc implements BookDAO {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {

        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Book book) {
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("isbn", book.getIsbn())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update("insert into books (title, isbn, author_id, genre_id) values " +
                "(:title, :isbn, :author_id, :genre_id)", parameters, kh);
        book.setId(kh.getKey().longValue());
        return kh.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject("select b.id as id, b.title as title, b.isbn as isbn, b.author_id as author_id, a.name as author_name," +
                    "b.genre_id as genre_id, g.name as genre_name from books b " +
                    "left outer join authors a on (b.author_id = a.id) " +
                    "left outer join genres g on (b.genre_id = g.id) where b.id = :id", params, new BookMapper()
            );
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Book book) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("isbn", book.getIsbn())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "update books set title=:title, isbn =:isbn,  author_id = :author_id, genre_id = :genre_id where id=:id", parameters
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id as id, b.title as title, b.isbn as isbn, b.author_id as author_id, a.name as author_name," +
                "b.genre_id as genre_id, g.name as genre_name from books b " +
                "left outer join authors a on (b.author_id = a.id) " +
                "left outer join genres g on (b.genre_id = g.id)", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            String isbn = resultSet.getString("isbn");

            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name"));

            return new Book(id, title, isbn, author, genre);
        }
    }


}
