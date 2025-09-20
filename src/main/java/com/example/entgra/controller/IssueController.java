package com.example.entgra.controller;



import com.example.entgra.dto.IssueDTO;
import com.example.entgra.entity.Issue;
import com.example.entgra.entity.IssueHistory;
import com.example.entgra.entity.IssueStatus;
import com.example.entgra.service.IssueService;
import com.example.entgra.service.IssueHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class IssueController {

    private final IssueService issueService;
    private final IssueHistoryService historyService;

    public IssueController(IssueService issueService, IssueHistoryService historyService) {
        this.issueService = issueService;
        this.historyService = historyService;
    }

    // Create Issue
    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }

    // Get All Issues
    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/test")
    public String getAllIssuesA() {
        return "issueService.getAllIssues()";
    }

    @GetMapping("/cat")
    public List<List<Issue>> getAllIssuesB() {
        List<Issue> todo=new ArrayList<>();
        List<Issue> inprogress=new ArrayList<>();
        List<Issue> done=new ArrayList<>();
        List<Issue> codereviws=new ArrayList<>();
        List<List<Issue>> re=new ArrayList<>();

        issueService.getAllIssues().forEach(issue -> {
            if(issue.getStatus()==IssueStatus.OPEN){
                todo.add(issue);
            }
            else if(issue.getStatus()==IssueStatus.IN_PROGRESS){
                inprogress.add(issue);
            }
            else if(issue.getStatus()==IssueStatus.WAITING_ON_CLIENT){
                done.add(issue);
            }
            else if(issue.getStatus()==IssueStatus.RESOLVED){
                codereviws.add(issue);
            }

        });
        re.add(todo);
        re.add(inprogress);
        re.add(done);
        re.add(codereviws);

        return re;
    }


//    @GetMapping("/dash")
//    public List<List<Issue>> getAllIssuesC() {
//        List<Issue> todo=new ArrayList<>();
//        List<Issue> inprogress=new ArrayList<>();
//        List<Issue> done=new ArrayList<>();
//        List<Issue> codereviws=new ArrayList<>();
//        List<List<Issue>> re=new ArrayList<>();
//
//        issueService.getAllIssues().forEach(issue -> {
//            if(issue.getStatus()==IssueStatus.OPEN){
//                todo.add(issue);
//            }
//            else if(issue.getStatus()==IssueStatus.IN_PROGRESS){
//                inprogress.add(issue);
//            }
//            else if(issue.getStatus()==IssueStatus.RESOLVED){
//                done.add(issue);
//            }
//            else if(issue.getStatus()==IssueStatus.WAITING_ON_CLIENT){
//                codereviws.add(issue);
//            }
//
//        });
//        re.add(todo);
//        re.add(inprogress);
//        re.add(done);
//        re.add(codereviws);
//
//        return re;
//    }


    // Get Issue by ID
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    // Update Issue details
    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable Long id, @RequestBody Issue issue) {
        return issueService.updateIssue(id, issue);
    }

    // Delete Issue
    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
    }

     // Update Issue Status
    @PutMapping("/{id}/status")
    public Issue updateStatus(@PathVariable Long id, @RequestBody IssueDTO status) {
        return issueService.updateStatus(id, IssueStatus.valueOf(status.getStatus()));

    }


}
