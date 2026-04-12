
package com.project.resumeanalyzer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ResumeanalyzerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeanalyzerApplication.class, args);
    }
        @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("\n=======================================");
            System.out.println(" Application Started Successfully!");
            System.out.println(" Open in browser: http://localhost:8080");
            System.out.println("=======================================\n");
        };
    }

}
