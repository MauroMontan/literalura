package mauro.montan.literalura.books.repositories;


import mauro.montan.literalura.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
}