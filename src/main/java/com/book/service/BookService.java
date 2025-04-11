package com.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Page<Book> getBooks(int page, int size, String sortBy) {
        return bookRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public Page<Book> searchByTitle(String title, int page, int size) {
        return bookRepository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(page, size)
        );
    }

    public Page<Book> searchByAuthor(String author, int page, int size) {
        return bookRepository.findByAuthorContainingIgnoreCase(
                author,
                PageRequest.of(page, size)
        );
    }
}

