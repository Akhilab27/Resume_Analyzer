package com.project.resumeanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.resumeanalyzer.Entity.ResumeEntity;
import com.project.resumeanalyzer.repository.ResumeRepository;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    public List<ResumeEntity> processMultipleResumes(MultipartFile[] files) throws Exception {

        List<ResumeEntity> results = new ArrayList<>();

        for (MultipartFile file : files) {

            // Extract text from PDF
            String text = ResumeParser.extractTextFromPDF(file.getInputStream());

            // Extract candidate email from resume text
            String candidateEmail = EmailExtractor.extractEmail(text);

            // Load required keywords
            List<String> keywords = SkillMatcher.loadKeywords("src/main/resources/data/job_keywords.txt");

            // Count matched skills
            int matched = SkillMatcher.countMatches(text, keywords);

            // Collect missing skills
            List<String> missing = new ArrayList<>();
            for (String k : keywords) {
                if (!text.toLowerCase().contains(k.toLowerCase())) {
                    missing.add(k);
                }
            }

            double percent = (matched * 100.0) / keywords.size();
            String status = percent >= 80 ? "SELECTED" : "REJECTED";

            ResumeEntity entity = new ResumeEntity(
                    file.getOriginalFilename(),
                    matched,
                    keywords.size(),
                    percent,
                    status,
                    String.join(", ", missing),
                    candidateEmail
            );

            repository.save(entity);
            results.add(entity);
        }

        return results;
    }

    public List<ResumeEntity> getAllResumes() {
        return repository.findAll();
    }
}
