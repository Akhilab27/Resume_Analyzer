package com.project.resumeanalyzer.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SkillMatcher {

    // ✅ Load keywords from resources folder
    public static List<String> loadKeywords(String path) throws Exception {
        List<String> keywords = new ArrayList<>();

        InputStream is = SkillMatcher.class.getClassLoader().getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("File not found: " + path);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    keywords.add(line.trim().toLowerCase());
                }
            }
        }
        return keywords;
    }

    // ✅ Count matched skills
    public static int countMatches(String text, List<String> keywords) {
        int count = 0;

        text = text.toLowerCase();

        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                count++;
            }
        }

        return count;
    }
}