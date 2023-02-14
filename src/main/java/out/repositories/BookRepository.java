package out.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import out.models.Book;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<List<Book>> findByNameStartingWith(String startingName);

}
