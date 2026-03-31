
package com.project.resumeanalyzer.repository;

import com.project.resumeanalyzer.Entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    List<ResumeEntity> findByStatus(String status);
}