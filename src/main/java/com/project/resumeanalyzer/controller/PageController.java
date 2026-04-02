
package com.project.resumeanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String loginPage() {
        return "redirect:/login.html";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "redirect:/dashboard.html";
    }
}