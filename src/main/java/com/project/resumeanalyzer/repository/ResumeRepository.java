
package com.project.resumeanalyzer.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String filename, double percent) {
        String sql = "INSERT INTO resume_results(filename, match_percentage) VALUES (?, ?)";
        jdbcTemplate.update(sql, filename, percent);
    }
}