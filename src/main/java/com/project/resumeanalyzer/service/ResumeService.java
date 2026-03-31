

package com.project.resumeanalyzer.service;

import com.project.resumeanalyzer.Entity.ResumeEntity;
import com.project.resumeanalyzer.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    public List<ResumeEntity> processMultipleResumes(MultipartFile[] files) throws Exception {

        List<ResumeEntity> results = new ArrayList<>();

        for (MultipartFile file : files) {

    // Directly use InputStream (FIXED)
    String text = ResumeParser.extractTextFromPDF(file.getInputStream());

    List<String> keywords = SkillMatcher.loadKeywords("src/main/resources/data/job_keywords.txt");

    int matched = SkillMatcher.countMatches(text, keywords);

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
            String.join(", ", missing)
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