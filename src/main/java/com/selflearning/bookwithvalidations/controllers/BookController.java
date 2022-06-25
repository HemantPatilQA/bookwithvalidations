package com.selflearning.bookwithvalidations.controllers;

import com.selflearning.bookwithvalidations.dtos.BookDTO;
import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
@Validated // class level
public class BookController {

    @Autowired private BookService bookService;
    @Autowired private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
        Book bookRequest = modelMapper.map(bookDTO, Book.class);
        Book book = bookService.addBook(bookRequest);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{bookname}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("bookname") @Min(2) String name){
        Book book = bookService.getBook(name);
        BookDTO bookResponse = modelMapper.map(book, BookDTO.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();

        List<BookDTO> bookDTOList = bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}
