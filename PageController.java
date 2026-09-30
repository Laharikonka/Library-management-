package com.library.controller;

import com.library.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final BookRepository books;
    private final MemberRepository members;
    private final TransactionRepository transactions;

    public PageController(BookRepository books, MemberRepository members, TransactionRepository transactions) {
        this.books = books; this.members = members; this.transactions = transactions;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("bookCount", books.count());
        model.addAttribute("memberCount", members.count());
        model.addAttribute("transactionCount", transactions.count());
        model.addAttribute("issuedCount", transactions.findByStatus("ISSUED").size());
        return "index";
    }
}
