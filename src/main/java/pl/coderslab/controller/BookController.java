package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.JsonResponse;
import pl.coderslab.domain.dto.BookDto;
import pl.coderslab.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    @Qualifier("memoryBookService")
    BookService service;

    @RequestMapping("")
    public List<Book> getAll(){
        return service.getAllBooks();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }


    @RequestMapping(value = "",method = RequestMethod.POST)
    public Book addBook(@RequestBody BookDto book){
       return service.addBook(book);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Book updateBook(@PathVariable Long id){
        return service.getBookById(id);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Book updateBook(@PathVariable Long id,@RequestBody BookDto book){
        return service.updateBook(id,book);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public JsonResponse deleteBook(@PathVariable Long id){
        return service.deleteBook(id);
    }
}
