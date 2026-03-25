package org.example.td23_spring.Controller;


import org.example.td23_spring.Model.Student;
import org.example.td23_spring.Service.StudentService;
import org.example.td23_spring.Validator.AcceptHeaderValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final AcceptHeaderValidator headerValidator;

    public StudentController(StudentService studentService, AcceptHeaderValidator headerValidator) {
        this.studentService = studentService;
        this.headerValidator = headerValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> newStudents) {
        try {
            List<Student> updatedStudents = studentService.addStudents(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudents);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = HttpHeaders.ACCEPT, required = false) String acceptHeader) {
        try {
            headerValidator.validate(acceptHeader);
            List<Student> students = studentService.getAllStudents();

            if (acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE)) {
                String text = studentService.convertStudentsToText(students);
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(text);
            } else {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(students);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}