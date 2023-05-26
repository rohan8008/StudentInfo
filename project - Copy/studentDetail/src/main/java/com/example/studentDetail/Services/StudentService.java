package com.example.studentDetail.Services;

import com.example.studentDetail.Model.Student;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    public List<Student> loadStudentData(String filterCriteria) {
        List<Student> students = new ArrayList<>();
        String filePath = "P:\\intellij\\book.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    String grade = data[2];

                    Student student = new Student();
                    student.setId(id);
                    student.setName(name);
                    student.setGrade(grade);

                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file loading error
            System.err.println("Error loading student data from file: " + e.getMessage());
        }

        // Apply filtering criteria
        if (filterCriteria != null && !filterCriteria.isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getName().contains(filterCriteria))
                    .collect(Collectors.toList());
        }

        return students;
    }
}
