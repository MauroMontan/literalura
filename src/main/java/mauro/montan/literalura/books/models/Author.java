package mauro.montan.literalura.books.models;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
@lombok.Data
@lombok.AllArgsConstructor
public class Author {

    public Author(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,nullable =true)
    private String name;

    @Column(name = "birth_year")
    private long birthYear;

    @Column(name = "death_year")
    private long deathYear;
}
