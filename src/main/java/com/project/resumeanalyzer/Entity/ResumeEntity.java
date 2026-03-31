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

    public ResumeEntity() {}

    public ResumeEntity(String fileName, int matched, int total, double percentage, String status, String missingSkills) {
        this.fileName = fileName;
        this.matched = matched;
        this.total = total;
        this.percentage = percentage;
        this.status = status;
        this.missingSkills = missingSkills;
    }

    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public int getMatched() { return matched; }
    public int getTotal() { return total; }
    public double getPercentage() { return percentage; }
    public String getStatus() { return status; }
    public String getMissingSkills() { return missingSkills; }

    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setMatched(int matched) { this.matched = matched; }
    public void setTotal(int total) { this.total = total; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
    public void setStatus(String status) { this.status = status; }
    public void setMissingSkills(String missingSkills) { this.missingSkills = missingSkills; }
}