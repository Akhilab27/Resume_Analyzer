package com.project.resumeanalyzer.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.resumeanalyzer.Entity.ResumeEntity;
import com.project.resumeanalyzer.repository.ResumeRepository;
import com.project.resumeanalyzer.service.EmailService;
import com.project.resumeanalyzer.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @Autowired
    private ResumeRepository repository;

    @Autowired
    private EmailService emailService;

    // ─── Analyze uploaded resumes ────────────────────────────────
    @PostMapping("/analyze")
    public List<ResumeEntity> analyze(@RequestParam("files") MultipartFile[] files) throws Exception {
        return service.processMultipleResumes(files);
    }

    // ─── Get all resume results ──────────────────────────────────
    @GetMapping("/all")
    public List<ResumeEntity> getAll() {
        return service.getAllResumes();
    }

    // ─── Send selection email to a candidate ────────────────────
    @PostMapping("/send-email/{id}")
    public ResponseEntity<Map<String, String>> sendEmail(@PathVariable Long id) {
        Optional<ResumeEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Resume not found with id: " + id));
        }

        ResumeEntity resume = optional.get();

        if (!"SELECTED".equals(resume.getStatus())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Cannot send email – candidate was not SELECTED."));
        }

        String email = resume.getCandidateEmail();
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No email address found in this resume. Please contact the candidate manually."));
        }

        try {
            emailService.sendSelectionEmail(email, resume.getFileName(), resume.getPercentage());
            resume.setEmailSent(true);
            repository.save(resume);
            return ResponseEntity.ok(Map.of(
                    "message", "Selection email sent successfully to " + email,
                    "email", email
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to send email: " + e.getMessage()));
        }
    }
}
