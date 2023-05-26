package com.example.studentDetail.Controller;

import com.example.studentDetail.Model.Student;
import com.example.studentDetail.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class StudentController {

    // Load student data from file (CSV, JSON, or any other format)
    @Autowired
    private StudentService studentService;

    // Retrieve student details in a paginated manner
    @GetMapping
    @RequestMapping("/students")
    public List<Student> getPaginatedStudents(@RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        List<Student> students = studentService.loadStudentData();

        // Calculate the start and end indexes for the requested page
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, students.size());

        // Return the sublist of students for the requested page
        if (startIndex < endIndex) {
            return students.subList(startIndex, endIndex);
        } else {
            return Collections.emptyList();
        }
    }
}
