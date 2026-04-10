package com.studentmgmt.service;

import com.studentmgmt.exception.DuplicateEmailException;
import com.studentmgmt.exception.StudentNotFoundException;
import com.studentmgmt.model.Student;
import com.studentmgmt.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // ── Read ──────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Student> search(String query) {
        if (query == null || query.isBlank()) return findAll();
        return repo.search(query.trim());
    }

    // ── Create ────────────────────────────────────────────

    public Student create(Student student) {
        // Check duplicate email
        repo.findByEmail(student.getEmail()).ifPresent(existing -> {
            throw new DuplicateEmailException(student.getEmail());
        });

        // Auto-generate unique ID: STU-XXXX
        String newId = generateId();
        student.setId(newId);

        return repo.save(student);
    }

    // ── Update ────────────────────────────────────────────

    public Student update(String id, Student updates) {
        Student existing = findById(id);

        // Email uniqueness check (ignore self)
        repo.findByEmail(updates.getEmail()).ifPresent(other -> {
            if (!other.getId().equals(id)) {
                throw new DuplicateEmailException(updates.getEmail());
            }
        });

        existing.setFirstName(updates.getFirstName());
        existing.setLastName(updates.getLastName());
        existing.setEmail(updates.getEmail());
        existing.setPhone(updates.getPhone());
        existing.setCourse(updates.getCourse());
        existing.setGrade(updates.getGrade());
        existing.setStatus(updates.getStatus());
        if (updates.getEnrolledAt() != null) {
            existing.setEnrolledAt(updates.getEnrolledAt());
        }

        return repo.save(existing);
    }

    // ── Delete ────────────────────────────────────────────

    public void delete(String id) {
        if (!repo.existsById(id)) throw new StudentNotFoundException(id);
        repo.deleteById(id);
    }

    // ── Stats ─────────────────────────────────────────────

    @Transactional(readOnly = true)
    public long countAll()      { return repo.count(); }

    @Transactional(readOnly = true)
    public long countActive()   { return repo.findByStatus("Active").size(); }

    @Transactional(readOnly = true)
    public long countInactive() { return repo.findByStatus("Inactive").size(); }

    // ── Helpers ───────────────────────────────────────────

    private String generateId() {
        Integer max = repo.findMaxSequence();
        int next = (max == null ? 0 : max) + 1;
        return String.format("STU-%04d", next);
    }
}
