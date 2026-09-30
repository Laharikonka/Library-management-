package com.library.controller;

import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final BookRepository books;
    private final MemberRepository members;
    private final TransactionRepository transactions;

    public DashboardController(BookRepository books, MemberRepository members, TransactionRepository transactions) {
        this.books = books; this.members = members; this.transactions = transactions;
    }

    @GetMapping
    public Map<String, Long> stats() {
        return Map.of(
            "books", books.count(),
            "members", members.count(),
            "transactions", transactions.count(),
            "issued", (long) transactions.findByStatus("ISSUED").size()
        );
    }
}
