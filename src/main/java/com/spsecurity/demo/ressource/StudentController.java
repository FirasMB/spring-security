package com.spsecurity.demo.ressource;

import com.spsecurity.demo.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1, "james bond"),
      new Student(2, "maria jones"),
      new Student(3, "anna smith")
    );

    @GetMapping(path= "{studentId}")
    public Student getStudentById(@PathVariable(name = "studentId") Integer studentId) {

        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "student "+ studentId + " does not exists!"
                ));
    }

}
