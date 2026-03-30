package com.project.resumeanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.resumeanalyzer.model.Result;
import com.project.resumeanalyzer.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService service;

    // ✅ API to analyze resume
    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(@RequestParam("file") MultipartFile file) {
        try {
             System.out.println("API HIT SUCCESS"); 
            // Call service layer
            Result result = service.processResume(file);

            // Return JSON response
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();

            // Return error message
            return ResponseEntity.badRequest().body("Error processing resume");
        }
    }
}