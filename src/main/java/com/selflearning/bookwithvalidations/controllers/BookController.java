package com.selflearning.bookwithvalidations.controllers;

import com.selflearning.bookwithvalidations.dtos.BookDTO;
import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.services.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
        logger.info("Adding Book : " + bookDTO.getName());
        Book bookRequest = modelMapper.map(bookDTO, Book.class);
        Book book = bookService.addBook(bookRequest);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);
        logger.info("Added Book : " + bookDTO.getName() + " Successfully.");
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{bookname}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("bookname") @Size(max = 10)String name){
        logger.info("Searching for book with name : " + name);
        Book book = bookService.getBook(name);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);
        logger.info("Book with name : " + name + " Searched successfully.");
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();

        List<BookDTO> bookDTOList = bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}
