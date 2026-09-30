package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) { this.repository = repository; }
    public List<Book> findAll() { return repository.findAll(); }
    public List<Book> search(String q) {
        return q == null || q.isBlank() ? repository.findAll() :
            repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q, q);
    }
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public Book save(Book book) { return repository.save(book); }
    public void delete(Long id) { repository.deleteById(id); }
}
