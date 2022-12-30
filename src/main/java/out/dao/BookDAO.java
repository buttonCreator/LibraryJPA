package out.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import out.models.Book;
import out.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getBookOwner(int id) {

        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Books ON Person.id_person = Books.id_person " +
                        "WHERE Books.id_book=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }

    public Book show(int id) {

        return jdbcTemplate.query("SELECT * FROM Books WHERE id_book=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO Books(id_person, nameBook, author, yearOfWriting) VALUES (null, ?, ?, ?)", book.getNameBook(),
                book.getAuthor(), book.getYearOfWriting());

    }

    public void update(int id, Book book) {

        jdbcTemplate.update("UPDATE Books SET nameBook=?, author=?, yearOfWriting=? WHERE id_book=?", book.getNameBook(), book.getAuthor(),
                book.getYearOfWriting(), id);

    }

    public void delete(int id_book) {

        jdbcTemplate.update("DELETE FROM Books WHERE id_book=?", id_book);

    }

    public void addId_person(int id_book, Person selectedPerson) {

        jdbcTemplate.update("UPDATE Books SET id_person=? WHERE id_book=?", selectedPerson.getId_person(), id_book);

    }

    public void release(int id) {

        jdbcTemplate.update("UPDATE Book SET id_person=NULL WHERE id_book=?", id);

    }

}
