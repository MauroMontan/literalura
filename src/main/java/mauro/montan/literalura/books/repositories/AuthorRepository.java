package mauro.montan.literalura.books.repositories;

import mauro.montan.literalura.books.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findByName(String name);
}
