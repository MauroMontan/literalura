package mauro.montan.literalura.books.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
@lombok.Data
@lombok.AllArgsConstructor
public class Book {

    public Book(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;


    private List<String> languages;

    @ManyToMany( fetch = FetchType.EAGER, targetEntity = Author.class,cascade = CascadeType.ALL)
    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }
}
