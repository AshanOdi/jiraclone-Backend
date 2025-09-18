package com.example.entgra.repository;

import com.example.entgra.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue , Long> {

}
