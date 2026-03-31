

package com.project.resumeanalyzer.controller;

import com.project.resumeanalyzer.Entity.ResumeEntity;
import com.project.resumeanalyzer.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @PostMapping("/analyze")
    public List<ResumeEntity> analyze(@RequestParam("files") MultipartFile[] files) throws Exception {
        return service.processMultipleResumes(files);
    }

    @GetMapping("/all")
    public List<ResumeEntity> getAll() {
        return service.getAllResumes();
    }
}