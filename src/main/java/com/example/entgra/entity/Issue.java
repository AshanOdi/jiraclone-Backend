package com.example.entgra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueType type;  // BUG, QUESTION, IMPROVEMENT

    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.OPEN; // OPEN, IN_PROGRESS, WAITING_ON_CLIENT, RESOLVED

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


}
