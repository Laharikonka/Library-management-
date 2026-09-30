package com.library.controller;

import com.library.model.Transaction;
import com.library.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;
    public TransactionController(TransactionService service) { this.service = service; }

    @GetMapping public List<Transaction> all() { return service.findAll(); }

    @PostMapping("/issue")
    public Transaction issue(@RequestParam Long bookId, @RequestParam Long memberId,
                             @RequestParam String dueDate) {
        return service.issue(bookId, memberId, LocalDate.parse(dueDate));
    }

    @PutMapping("/{id}/return")
    public Transaction returnBook(@PathVariable Long id) { return service.returnBook(id); }
}
