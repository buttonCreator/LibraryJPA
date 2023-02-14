package out.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.models.Book;
import out.models.Person;
import out.repositories.BookRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(Integer page, Integer booksPerPage, Boolean sortByYear) {

        if (page != null)
            if (sortByYear != null)
                return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfWriting"))).getContent();
            else
                return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        else if (sortByYear != null)
            return bookRepository.findAll(Sort.by("yearOfWriting"));
        else
            return bookRepository.findAll();


    }

    public Book getBook(int id) {

        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);

    }

    @Transactional
    public Person getBookOwner(int id) {

        return bookRepository.findById(id).map(Book::getOwner).orElse(null);

    }

    @Transactional
    public void save(Book book) {

        bookRepository.save(book);

    }

    @Transactional
    public void update(Book updateBook, int id) {

        updateBook.setId(id);
        bookRepository.save(updateBook);

    }

    @Transactional
    public void delete(int id) {

        bookRepository.deleteById(id);

    }

    @Transactional
    public void release(int id) {

        Book book = getBook(id);
        book.setPerson_id(null);
        book.setDateOfTaking(null);
        bookRepository.save(book);

    }

    @Transactional
    public void addId_person(int id, Person selectedPerson) {

        Book book = getBook(id);
        book.setPerson_id(selectedPerson);
        book.setDateOfTaking(new Date());
        bookRepository.save(book);

    }

    public List<Book> searchBook(String startingString) {

        return bookRepository.findByNameStartingWith(startingString);

    }

}
