package pl.coderslab.service;

import pl.coderslab.domain.Book;
import pl.coderslab.domain.JsonResponse;
import pl.coderslab.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(long id);
    Book addBook(BookDto book);
    Book updateBook(long id,BookDto book);
    JsonResponse deleteBook(long id);
}
