package com.library.service;

import com.library.model.*;
import com.library.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactions;
    private final BookRepository books;
    private final MemberRepository members;

    public TransactionService(TransactionRepository transactions, BookRepository books, MemberRepository members) {
        this.transactions = transactions;
        this.books = books;
        this.members = members;
    }

    public List<Transaction> findAll() { return transactions.findAll(); }

    @Transactional
    public Transaction issue(Long bookId, Long memberId, LocalDate dueDate) {
        Book book = books.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = members.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        if (book.getAvailableQuantity() <= 0) throw new RuntimeException("Book is not available");

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        books.save(book);

        Transaction t = new Transaction();
        t.setBook(book);
        t.setMember(member);
        t.setIssueDate(LocalDate.now());
        t.setDueDate(dueDate);
        t.setStatus("ISSUED");
        return transactions.save(t);
    }

    @Transactional
    public Transaction returnBook(Long id) {
        Transaction t = transactions.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        if ("RETURNED".equals(t.getStatus())) throw new RuntimeException("Book already returned");

        Book book = t.getBook();
        book.setAvailableQuantity(Math.min(book.getQuantity(), book.getAvailableQuantity() + 1));
        books.save(book);

        t.setReturnDate(LocalDate.now());
        t.setStatus("RETURNED");
        return transactions.save(t);
    }
}
