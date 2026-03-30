# Resume Skill Matching and Analysis System

## Project Overview
This project is a web-based application developed using Java Spring Boot that analyzes resumes by extracting text from PDF files and matching candidate skills with predefined job requirements.

## Features
- Upload resume in PDF format
- Extract text using Apache PDFBox
- Match skills with job keywords
- Calculate match percentage
- Display missing skills for improvement

## Technologies Used
- Backend: Java, Spring Boot
- Frontend: HTML, CSS, JavaScript, Bootstrap
- Libraries: Apache PDFBox
- Build Tool: Maven

## How It Works
1. User uploads a resume (PDF)
2. System extracts text from the file
3. Keywords are loaded from predefined dataset
4. Skills are matched with resume content
5. Results are displayed with:
   - Matched skills
   - Match percentage
   - Missing skills

## Live Demo
(Will be added after deployment)

## Project Structure
- Controller → Handles API requests
- Service → Business logic (parsing & matching)
- Model → Result data structure
- Static → Frontend UI

## Purpose
To simulate an automated resume screening system used in recruitment processes.

## Author
Akhila Badiginchala