package com.studentmgmt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Course is required")
    @Column(name = "course", nullable = false)
    private String course;

    @Column(name = "grade")
    private String grade;

    @Column(name = "status", nullable = false)
    private String status = "Active";

    @Column(name = "enrolled_at")
    private LocalDate enrolledAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Lifecycle ──────────────────────────────────────────

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (enrolledAt == null) enrolledAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ── Constructors ──────────────────────────────────────

    public Student() {}

    public Student(String id, String firstName, String lastName, String email,
                   String phone, String course, String grade, String status, LocalDate enrolledAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.grade = grade;
        this.status = status;
        this.enrolledAt = enrolledAt;
    }

    // ── Getters & Setters ─────────────────────────────────

    public String getId()                    { return id; }
    public void setId(String id)             { this.id = id; }

    public String getFirstName()             { return firstName; }
    public void setFirstName(String v)       { this.firstName = v; }

    public String getLastName()              { return lastName; }
    public void setLastName(String v)        { this.lastName = v; }

    public String getEmail()                 { return email; }
    public void setEmail(String v)           { this.email = v; }

    public String getPhone()                 { return phone; }
    public void setPhone(String v)           { this.phone = v; }

    public String getCourse()                { return course; }
    public void setCourse(String v)          { this.course = v; }

    public String getGrade()                 { return grade; }
    public void setGrade(String v)           { this.grade = v; }

    public String getStatus()                { return status; }
    public void setStatus(String v)          { this.status = v; }

    public LocalDate getEnrolledAt()         { return enrolledAt; }
    public void setEnrolledAt(LocalDate v)   { this.enrolledAt = v; }

    public LocalDateTime getCreatedAt()      { return createdAt; }
    public LocalDateTime getUpdatedAt()      { return updatedAt; }
}
