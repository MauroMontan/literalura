package mauro.montan.literalura.cli;

import mauro.montan.literalura.books.BookService;
import mauro.montan.literalura.books.dtos.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class CLIHandler {

    @Autowired
    private BookService service;
    public void menuBanner(){

        System.out.println("Literalura");

        System.out.println("1.- Buscar pelicula");
    }

    public void menuLoop() {
        Integer option;

        try(Scanner scanner = new Scanner(System.in)) {

            do {
                System.out.println("ingresa una opción del menu");
                 option = scanner.nextInt();

                switch ( option ) {
                    case 1:
                        search(scanner);
                        break;
                    default:
                        System.out.println("Esta no es una opción valida");
                        break;
                }
            } while ( option != 0 );

        } catch (InputMismatchException e) {
            option = 1;
            System.out.println("ingresa una opción valida");

        }
    }

    public void search(Scanner scanner) {

            System.out.println("bucar: ");
            String searchInput = scanner.nextLine();

            System.out.println("-" + searchInput + "-");
            List<BookDto> bookSearch = service.search(searchInput);

            System.out.println("Encontramos estos libros para ti!");
            System.out.println(" ");

            bookSearch.forEach(bookDto -> {
                System.out.println(bookDto.getTitle());
            });



    }

}
