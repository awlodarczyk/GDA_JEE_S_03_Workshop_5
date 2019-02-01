package pl.coderslab.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.JsonResponse;
import pl.coderslab.domain.dto.BookDto;
import pl.coderslab.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class MemoryBookService implements BookService {

    private List<Book> books;

    public MemoryBookService() {
        books = new ArrayList<>();

        books.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        books.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    @Override
    public List<Book> getAllBooks(){
        return books;
    }

    @Override
    public Book getBookById(long id) {

        for(Book book: getAllBooks()) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book addBook(BookDto book) {
        if(book.getAuthor()==null) throw new RuntimeException("No author");
        if(book.getIsbn()==null) throw new RuntimeException("No ISBN");
        if(book.getTitle()==null) throw new RuntimeException("No title");
        if(book.getType()==null) throw new RuntimeException("No type");
        if(book.getPublisher()==null) throw new RuntimeException("No publisher");
        Book last = books.stream().sorted(((o1, o2) -> o2.getId().compareTo(o1.getId()))).findFirst().get();
        Book book1 = new Book(last.getId()+1,book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(), book.getType());
        books.add(book1);
        return book1;
    }

    @Override
    public Book updateBook(long id,BookDto book) {
        Book found = getBookById(id);
        if(found!=null){
            found.setAuthor(book.getAuthor());
            found.setTitle(book.getTitle());
            found.setType(book.getType());
            found.setPublisher(book.getPublisher());
            found.setIsbn(book.getIsbn());
        }

        return found;
    }

    @Override
    public JsonResponse deleteBook(long id) {
        Book found = getBookById(id);
        if(found!=null){
                books.remove(found);
                return new JsonResponse("success","Removed book id: "+id);
        }
        return new JsonResponse("error","Book not found");
    }

}
