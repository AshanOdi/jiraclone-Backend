package com.example.entgra.repository;

import com.example.entgra.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory , Long> {
    List<IssueHistory> findByIssueId(Long issueId);
}
