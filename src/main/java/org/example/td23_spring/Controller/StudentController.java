package org.example.td23_spring.Controller;


import org.example.td23_spring.Model.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final List<Student> students = new ArrayList<>();


    @PostMapping("/students")
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> newStudents) {
        try {
            students.addAll(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(students);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = HttpHeaders.ACCEPT, required = false) String acceptHeader) {
        try {
            if (acceptHeader == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("L'en-tête 'Accept' est requis.");
            }

            if (!acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE) && !acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Type de contenu non supporté. Utilisez 'text/plain' ou 'application/json'.");
            }

            if (acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE)) {
                StringBuilder sb = new StringBuilder();
                for (Student s : students) {
                    sb.append("Référence: ").append(s.reference())
                            .append(", Prénom: ").append(s.firstName())
                            .append(", Nom: ").append(s.lastName())
                            .append(", Âge: ").append(s.age())
                            .append(" ans\n");
                }
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(sb.toString());
            } else {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(students);
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}