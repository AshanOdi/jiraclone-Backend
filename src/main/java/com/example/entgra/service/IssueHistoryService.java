package com.example.entgra.service;

import com.example.entgra.entity.IssueHistory;
import com.example.entgra.repository.IssueHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueHistoryService {

    private final IssueHistoryRepository historyRepository;

    public IssueHistoryService(IssueHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<IssueHistory> getHistoryByIssueId(Long issueId) {
        return historyRepository.findByIssueId(issueId);
    }
}
