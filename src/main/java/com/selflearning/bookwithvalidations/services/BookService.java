package com.selflearning.bookwithvalidations.services;

import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
