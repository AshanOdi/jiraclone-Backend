package com.example.entgra.service;

import com.example.entgra.entity.Issue;
import com.example.entgra.entity.IssueHistory;
import com.example.entgra.entity.IssueStatus;
import com.example.entgra.repository.IssueRepository;
import com.example.entgra.repository.IssueHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueHistoryRepository historyRepository;

    public IssueService(IssueRepository issueRepository, IssueHistoryRepository historyRepository) {
        this.issueRepository = issueRepository;
        this.historyRepository = historyRepository;
    }

    public Issue createIssue(Issue issue) {
        issue.setStatus(IssueStatus.OPEN);
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());
        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
    }

    public Issue updateIssue(Long id, Issue updatedIssue) {
        Issue issue = getIssueById(id);
        issue.setTitle(updatedIssue.getTitle());
        issue.setCustomer(updatedIssue.getCustomer());
        issue.setDescription(updatedIssue.getDescription());
        issue.setType(updatedIssue.getType());
        issue.setStatus(updatedIssue.getStatus());
        issue.setUpdatedAt(LocalDateTime.now());
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public Issue updateStatus(Long id, IssueStatus newStatus) {
        Issue issue = getIssueById(id);
        IssueStatus oldStatus = issue.getStatus();

        issue.setStatus(newStatus);
        issue.setUpdatedAt(LocalDateTime.now());
        Issue saved = issueRepository.save(issue);

        // save history
        IssueHistory history = new IssueHistory();
        history.setIssue(saved);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        historyRepository.save(history);

        return saved;
    }
}
