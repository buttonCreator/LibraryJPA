package out.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.models.Book;
import out.models.Person;
import out.repositories.PeopleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getPeople() {

        return peopleRepository.findAll();

    }

    public Person findOne(int id) {

        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);

    }

    @Transactional
    public void save(Person person) {

        peopleRepository.save(person);

    }
    @Transactional
    public void update(Person updatePerson, int id) {

        updatePerson.setId(id);
        peopleRepository.save(updatePerson);

    }

    @Transactional
    public void delete(int id) {

        peopleRepository.deleteById(id);

    }

    @Transactional
    public Optional<Person> findPersonByName(String name) {

        return peopleRepository.findPersonByName(name);

    }

    @Transactional
    public List<Book> getBooksByPersonId(int id) {

        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {

            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();

        } else
            return Collections.emptyList();

    }

}
