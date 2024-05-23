package mauro.montan.literalura.books.dtos;

import mauro.montan.literalura.books.mappers.AuthorMapper;
import mauro.montan.literalura.books.models.Author;
import mauro.montan.literalura.books.models.Book;

import java.util.List;

@lombok.Data
public class BookDto {
    private long id;
    private String title;
    private List<AuthorDto> authors;
    private List<Object> translators;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private boolean copyright;
    private String mediaType;
    private Formats formats;
    private long downloadCount;


    public BookDto fromDatabase(Book book) {


        if(book.getAuthors() != null){
            List<AuthorDto> authors = book.getAuthors().stream().map( author -> {
                var mapAuthor = new AuthorDto();
                mapAuthor.setName(author.getName());
                mapAuthor.setBirthYear(author.getBirthYear());
                mapAuthor.setDeathYear(author.getDeathYear());
                return mapAuthor;
            } ).toList();
            this.setAuthors(authors);
        }
        this.setTitle(book.getTitle());

        return this;
    }
}
