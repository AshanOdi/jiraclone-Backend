package com.example.entgra.dto;

import java.time.LocalDateTime;

public class IssueHistoryDTO {
    private Long id;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime changedAt;
}
