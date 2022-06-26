package com.selflearning.bookwithvalidations.services;

import com.selflearning.bookwithvalidations.dtos.BookDTO;
import com.selflearning.bookwithvalidations.entities.Book;
import com.selflearning.bookwithvalidations.error.ResourceNotFoundException;
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

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    public Book updateBook(Long bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());

        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }
}
