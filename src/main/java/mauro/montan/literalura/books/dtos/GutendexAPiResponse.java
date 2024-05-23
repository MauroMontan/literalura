package mauro.montan.literalura.books.dtos;

import java.util.List;

@lombok.Data
public class GutendexAPiResponse {
    private long count;
    private String next;
    private String previous;
    private List<BookDto> results;
}
