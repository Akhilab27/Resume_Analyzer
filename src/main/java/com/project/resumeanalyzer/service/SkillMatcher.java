
package com.project.resumeanalyzer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SkillMatcher {

    // Load keywords from file
    public static List<String> loadKeywords(String filePath) throws Exception {
        List<String> keywords = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                keywords.add(line.trim().toLowerCase());
            }
        }

        reader.close();
        return keywords;
    }

    // Count matching skills
    public static int countMatches(String text, List<String> keywords) {

        int count = 0;
        text = text.toLowerCase();

        for (String skill : keywords) {
            if (text.contains(skill)) {
                count++;
            }
        }

        return count;
    }
}