package com.project.resumeanalyzer.model;

import java.util.List;

public class Result {

    private int matched;
    private int total;
    private double percentage;
    private List<String> missingSkills;

    // Constructor
    public Result(int matched, int total, double percentage, List<String> missingSkills) {
        this.matched = matched;
        this.total = total;
        this.percentage = percentage;
        this.missingSkills = missingSkills;
    }

    // Getters
    public int getMatched() {
        return matched;
    }

    public int getTotal() {
        return total;
    }

    public double getPercentage() {
        return percentage;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMatched(int matched) {
        this.matched = matched;
    }
}