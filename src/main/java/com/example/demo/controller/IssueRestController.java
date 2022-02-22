package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.IssueReport;
import com.example.demo.repositories.IssueRepository;


@RestController
@RequestMapping("/api/issues")
public class IssueRestController {
    private IssueRepository issueRepository;

    public IssueRestController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping
    public List<IssueReport> getIssues() {
        return this.issueRepository.findAllButPrivate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueReport> getIssue(@PathVariable("id") Optional<IssueReport> issueReportOptional) {
        if (!issueReportOptional.isPresent() ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(issueReportOptional.get(), HttpStatus.OK);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public  ResponseEntity<IssueReport> newIssue(@RequestBody IssueReport issueReport){
        return  new ResponseEntity<>(this.issueRepository.save(issueReport),HttpStatus.CREATED);



    }
}

