package out.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import out.models.Book;
import out.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id_person) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE id_person=?", new Object[]{id_person},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO person(name, yearOfBirth) VALUES (?, ?)", person.getName(),
                person.getYearOfBirth());

    }

    public void update(int id, Person person) {

        jdbcTemplate.update("UPDATE Person SET name=?, yearOfBirth=? WHERE id_person=?", person.getName(),
                person.getYearOfBirth(), id);

    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM Person WHERE id_person=?", id);

    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id_person=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

}
