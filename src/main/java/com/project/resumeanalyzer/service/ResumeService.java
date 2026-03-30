package com.project.resumeanalyzer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.resumeanalyzer.model.Result;

@Service
public class ResumeService {

    public Result processResume(MultipartFile file) throws Exception {

        // ✅ Convert uploaded file → temp file
        File tempFile = File.createTempFile("resume", ".pdf");
        file.transferTo(tempFile);

        // ✅ Extract text from PDF
        String text = ResumeParser.extractTextFromPDF(tempFile.getAbsolutePath());

        // ✅ Load keywords from resources
        List<String> keywords = SkillMatcher.loadKeywords("data/job_keywords.txt");

        // ✅ Count matched skills
        int matched = SkillMatcher.countMatches(text, keywords);

        // ✅ Find missing skills
        List<String> missing = new ArrayList<>();
        for (String keyword : keywords) {
            if (!text.toLowerCase().contains(keyword)) {
                missing.add(keyword);
            }
        }

        // ✅ Calculate percentage
        double percentage = 0;
        if (!keywords.isEmpty()) {
            percentage = (matched * 100.0) / keywords.size();
        }

        // ✅ Return result
        return new Result(matched, keywords.size(), percentage, missing);
    }
}