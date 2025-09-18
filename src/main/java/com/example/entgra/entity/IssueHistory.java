package com.example.entgra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class IssueHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Issue issue;

    @Enumerated(EnumType.STRING)
    private IssueStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private IssueStatus newStatus;

    private LocalDateTime changedAt = LocalDateTime.now();

}
