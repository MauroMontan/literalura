package mauro.montan.literalura.books.mappers;

import mauro.montan.literalura.books.dtos.AuthorDto;
import mauro.montan.literalura.books.dtos.BookDto;
import mauro.montan.literalura.books.models.Author;
import mauro.montan.literalura.books.models.Book;

public class BookMapper {
    private Book book = new Book();

    public BookMapper(BookDto bookDto){
        book.setTitle(bookDto.getTitle());
        book.setLanguages(bookDto.getLanguages());

    }

    public Book getBook() {
        return book;
    }
}
