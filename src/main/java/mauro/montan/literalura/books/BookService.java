package mauro.montan.literalura.books;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import mauro.montan.literalura.books.dtos.BookDto;
import mauro.montan.literalura.books.dtos.GutendexAPiResponse;
import mauro.montan.literalura.books.mappers.AuthorMapper;
import mauro.montan.literalura.books.mappers.BookMapper;
import mauro.montan.literalura.books.models.Author;
import mauro.montan.literalura.books.models.Book;
import mauro.montan.literalura.books.repositories.AuthorRepository;
import mauro.montan.literalura.books.repositories.BookRepository;
import mauro.montan.literalura.http.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Gson gjson = new Gson();
    private final String API_URL = "https://gutendex.com";

    public GutendexAPiResponse loadBooks(Integer... page) throws IllegalArgumentException {

        if (page.length > 1) {
            throw new IllegalArgumentException("you can only provide one page");
        }

        var currentPage = page.length == 0 ? 1 : page[0];

        var response = HttpService.get(API_URL + "/books/?page=" + currentPage);

        var apiReponse = gjson.fromJson(response, GutendexAPiResponse.class);

        return apiReponse;
    }

    public List<BookDto> getBooks(){
        GutendexAPiResponse response = this.loadBooks();
        return response.getResults();
    }

    public List<BookDto> getBooksByPage(Integer page) throws IllegalArgumentException  {
        GutendexAPiResponse resp = this.loadBooks(page);
        return resp.getResults();
    }


    @Transactional
    public List<BookDto> search(String query) {
           String trimQuery = query.replaceAll(" ", "%20");
           var response = HttpService.get(API_URL + "/books/?search=" + trimQuery);
           var apiResponse = gjson.fromJson(response, GutendexAPiResponse.class);

           var results = apiResponse.getResults();


           return results;
    }

    @Transactional
    public void persist(BookDto book) {

        String authorName = book.getAuthors().isEmpty() ? null : book.getAuthors().get(0).getName();

        Optional<Author> author = Optional.ofNullable( authorRepository.findByName(authorName) );

        BookMapper bookMapper = new BookMapper(book);


        if (author.isPresent()) {
            bookMapper.getBook().setAuthors(List.of(author.get()));
        } else {
            AuthorMapper authorMapper = new AuthorMapper(book.getAuthors());
            bookMapper.getBook().setAuthors(authorMapper.getAuthors());
        }
        bookRepository.save(bookMapper.getBook());
    }

    public void remove(){
        bookRepository.deleteAll();
    }
}
