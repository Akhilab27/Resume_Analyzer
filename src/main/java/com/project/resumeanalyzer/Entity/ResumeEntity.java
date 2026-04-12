package com.project.resumeanalyzer.Entity;

import jakarta.persistence.*;

@Entity
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private int matched;
    private int total;
    private double percentage;
    private String status; // SELECTED / REJECTED

    @Column(length = 2000)
    private String missingSkills;

    // Email extracted from the candidate's resume PDF
    private String candidateEmail;

    // Track whether the selection email has been sent
    private boolean emailSent = false;

    public ResumeEntity() {}

    public ResumeEntity(String fileName, int matched, int total, double percentage,
                        String status, String missingSkills, String candidateEmail) {
        this.fileName = fileName;
        this.matched = matched;
        this.total = total;
        this.percentage = percentage;
        this.status = status;
        this.missingSkills = missingSkills;
        this.candidateEmail = candidateEmail;
        this.emailSent = false;
    }

    public Long getId()               { return id; }
    public String getFileName()       { return fileName; }
    public int getMatched()           { return matched; }
    public int getTotal()             { return total; }
    public double getPercentage()     { return percentage; }
    public String getStatus()         { return status; }
    public String getMissingSkills()  { return missingSkills; }
    public String getCandidateEmail() { return candidateEmail; }
    public boolean isEmailSent()      { return emailSent; }

    public void setFileName(String fileName)          { this.fileName = fileName; }
    public void setMatched(int matched)               { this.matched = matched; }
    public void setTotal(int total)                   { this.total = total; }
    public void setPercentage(double percentage)      { this.percentage = percentage; }
    public void setStatus(String status)              { this.status = status; }
    public void setMissingSkills(String missingSkills){ this.missingSkills = missingSkills; }
    public void setCandidateEmail(String email)       { this.candidateEmail = email; }
    public void setEmailSent(boolean emailSent)       { this.emailSent = emailSent; }
}
