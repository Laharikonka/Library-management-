package com.library.controller;

import com.library.model.Member;
import com.library.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;
    public MemberController(MemberService service) { this.service = service; }

    @GetMapping public List<Member> all() { return service.findAll(); }
    @GetMapping("/{id}") public Member one(@PathVariable Long id) { return service.findById(id); }
    @PostMapping public Member create(@Valid @RequestBody Member member) { return service.save(member); }
    @PutMapping("/{id}") public Member update(@PathVariable Long id, @Valid @RequestBody Member member) {
        member.setId(id); return service.save(member);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
