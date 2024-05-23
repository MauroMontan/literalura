package mauro.montan.literalura.books.dtos;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class AuthorDto {

    private String name;
    @SerializedName("birth_year")
    private long birthYear;
    @SerializedName("death_year")
    private long deathYear;
}
