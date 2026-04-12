package com.project.resumeanalyzer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    /**
     * Sends a selection notification email to the candidate.
     *
     * @param toEmail      Candidate's email extracted from the resume
     * @param resumeName   File name of the resume
     * @param matchPercent Match percentage scored
     */
    public void sendSelectionEmail(String toEmail, String resumeName, double matchPercent) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("Congratulations! Your Resume Has Been Selected – Interview Screening Call");

        String htmlBody = """
            <div style="font-family: Segoe UI, Arial, sans-serif; max-width: 600px; margin: auto;
                        border: 1px solid #ddd; border-radius: 10px; overflow: hidden;">

              <!-- Header -->
              <div style="background: #1f4037; padding: 25px 30px; color: white;">
                <h2 style="margin: 0;">📄 Smart Resume Analyzer</h2>
                <p style="margin: 5px 0 0; opacity: 0.8; font-size: 13px;">Recruitment Team</p>
              </div>

              <!-- Body -->
              <div style="padding: 30px; background: #f9f9f9;">
                <p style="font-size: 16px;">Dear Candidate,</p>

                <p style="font-size: 15px; line-height: 1.7;">
                  We are pleased to inform you that your resume
                  <strong>(%s)</strong> has been reviewed and
                  <span style="color: green; font-weight: bold;">SELECTED</span>
                  with a match score of
                  <strong style="color: #1f4037;">%.2f%%</strong>.
                </p>

                <p style="font-size: 15px; line-height: 1.7;">
                  Our HR team will be reaching out to schedule a
                  <strong>screening call</strong> with you shortly.
                  Please keep your phone and email accessible for further communication.
                </p>

                <div style="background: #e8f5e9; border-left: 4px solid #1f4037;
                            padding: 15px 20px; border-radius: 5px; margin: 20px 0;">
                  <p style="margin: 0; font-size: 14px; color: #1f4037;">
                    📞 <strong>Next Step:</strong> You will receive a call for a preliminary
                    screening interview. Please be prepared to discuss your skills and experience.
                  </p>
                </div>

                <p style="font-size: 15px; line-height: 1.7;">
                  We look forward to connecting with you. Thank you for your interest!
                </p>

                <p style="margin-top: 30px; font-size: 15px;">
                  Warm regards,<br/>
                  <strong>HR Recruitment Team</strong><br/>
                  <span style="color: gray; font-size: 13px;">Smart Resume Analyzer</span>
                </p>
              </div>

              <!-- Footer -->
              <div style="background: #1f4037; padding: 12px 30px; text-align: center;">
                <p style="margin: 0; color: rgba(255,255,255,0.6); font-size: 12px;">
                  This is an automated notification from the Smart Resume Analyzer system.
                </p>
              </div>

            </div>
            """.formatted(resumeName, matchPercent);

        helper.setText(htmlBody, true); // true = HTML
        mailSender.send(message);
    }
}
