
package com.project.resumeanalyzer.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to extract an email address from resume text.
 */
public class EmailExtractor {

    // Standard email regex pattern
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}");

    /**
     * Returns the first email address found in the given text, or null if none found.
     */
    public static String extractEmail(String text) {
        if (text == null || text.isBlank()) return null;
        Matcher matcher = EMAIL_PATTERN.matcher(text);
        return matcher.find() ? matcher.group() : null;
    }
}
