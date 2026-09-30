package com.library.service;

import com.library.model.Member;
import com.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;
    public MemberService(MemberRepository repository) { this.repository = repository; }
    public List<Member> findAll() { return repository.findAll(); }
    public Member findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }
    public Member save(Member member) { return repository.save(member); }
    public void delete(Long id) { repository.deleteById(id); }
}
