package mauro.montan.literalura.books.mappers;

import mauro.montan.literalura.books.dtos.AuthorDto;
import mauro.montan.literalura.books.models.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    private List<Author> authors = new ArrayList<>();
    public AuthorMapper(List<AuthorDto> authorDtos ){

        authorDtos.forEach(authorDto -> {
            Author author = new Author();
            author.setName(authorDto.getName());
            author.setBirthYear(authorDto.getBirthYear());
            author.setDeathYear(authorDto.getDeathYear());
            authors.add(author);
        });
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
