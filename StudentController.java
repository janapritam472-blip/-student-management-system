package com.studentmgmt.controller;

import com.studentmgmt.model.Student;
import com.studentmgmt.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")   // tighten in production
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET /api/students          — list all
    // GET /api/students?q=query  — search
    @GetMapping
    public ResponseEntity<List<Student>> list(
            @RequestParam(required = false) String q) {
        return ResponseEntity.ok(service.search(q));
    }

    // GET /api/students/stats
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> stats() {
        return ResponseEntity.ok(Map.of(
                "total",    service.countAll(),
                "active",   service.countActive(),
                "inactive", service.countInactive()
        ));
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getOne(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        Student created = service.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable String id,
            @Valid @RequestBody Student student) {
        return ResponseEntity.ok(service.update(id, student));
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Student " + id + " deleted successfully"));
    }
}
