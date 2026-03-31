

package com.project.resumeanalyzer.model;

import java.util.List;

public class Result {

    private int matched;
    private int total;
    private double percentage;
    private List<String> missingSkills;

    public Result(int matched, int total, double percentage, List<String> missingSkills) {
        this.matched = matched;
        this.total = total;
        this.percentage = percentage;
        this.missingSkills = missingSkills;
    }

    public int getMatched() { return matched; }
    public int getTotal() { return total; }
    public double getPercentage() { return percentage; }
    public List<String> getMissingSkills() { return missingSkills; }
}