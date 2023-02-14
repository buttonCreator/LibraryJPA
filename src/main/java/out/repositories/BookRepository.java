package out.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import out.models.Book;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByNameStartingWith (String startingString);

}
