package com.selflearning.bookwithvalidations.repositories;

import com.selflearning.bookwithvalidations.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    //Page<Book> findByUserId(Long userId, Pageable pageable);
    List<Book> findByUserId(Long userId);
    Optional<Book> findByIdAndUserId(Long id, Long userId);

    Page<Book> findByUserId(Long userId, Pageable pageable);
}
