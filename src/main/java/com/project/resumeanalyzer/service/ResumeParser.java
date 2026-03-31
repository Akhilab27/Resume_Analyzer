

package com.project.resumeanalyzer.service;

import java.io.InputStream;

public class ResumeParser {

    public static String extractTextFromPDF(InputStream inputStream) throws Exception {
        org.apache.pdfbox.pdmodel.PDDocument document =
                org.apache.pdfbox.pdmodel.PDDocument.load(inputStream);

        org.apache.pdfbox.text.PDFTextStripper pdfStripper =
                new org.apache.pdfbox.text.PDFTextStripper();

        String text = pdfStripper.getText(document);
        document.close();

        return text;
    }
}