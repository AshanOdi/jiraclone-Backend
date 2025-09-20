package com.example.entgra.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String customer;

    @Enumerated(EnumType.STRING)
    private IssueType type;  // BUG, QUESTION, IMPROVEMENT

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<IssueHistory> histories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.OPEN; // OPEN, IN_PROGRESS, WAITING_ON_CLIENT, RESOLVED


    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


}
