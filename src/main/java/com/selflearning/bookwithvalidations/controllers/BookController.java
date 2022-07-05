package com.selflearning.bookwithvalidations.controllers;

import com.selflearning.bookwithvalidations.dtos.BookDTO;
import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.error.ResourceNotFoundException;
import com.selflearning.bookwithvalidations.repositories.BookRepository;
import com.selflearning.bookwithvalidations.repositories.UserRepository;
import com.selflearning.bookwithvalidations.services.BookService;
import com.selflearning.bookwithvalidations.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
@Validated // class level
public class BookController {

    @Autowired private BookService bookService;
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private BookRepository bookRepository;
    @Autowired private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    /*@PostMapping("/books")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
        logger.info("Adding Book : " + bookDTO.getName());
        Book bookRequest = modelMapper.map(bookDTO, Book.class);
        Book book = bookService.addBook(bookRequest);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);
        logger.info("Added Book : " + bookDTO.getName() + " Successfully.");
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/books/{bookname}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("bookname") @Size(max = 10)String name){
        logger.info("Searching for book with name : " + name);
        Book book = bookService.getBook(name);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);
        logger.info("Book with name : " + name + " Searched successfully.");
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();

        List<BookDTO> bookDTOList = bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Long bookId){
        return new ResponseEntity<>(modelMapper.map(bookService.getBookById(bookId), BookDTO.class), HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBookById(@PathVariable(value = "id") Long bookId, @Valid @RequestBody BookDTO bookDTO){
        Book updatedBook = bookService.updateBook(bookId, bookDTO);

        return new ResponseEntity<>(modelMapper.map(updatedBook, BookDTO.class), HttpStatus.OK);
    }*/

    @GetMapping("/users/{userId}/booksPage")
    public Page<Book> getAllBooksByUserIdPage(@PathVariable (value = "userId") Long userId,
                                          Pageable pageable) {
        return bookService.findByUserId(userId, pageable);
    }

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<List<Book>> getAllBooksByUserId(@PathVariable (value = "userId") Long userId) {
        return new ResponseEntity<>(bookService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/books")
    public ResponseEntity<Book> addBook(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Book book) {
        return userRepository.findById(userId).map(user-> {
            book.setUser(user);
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id" , userId));
    }
}
