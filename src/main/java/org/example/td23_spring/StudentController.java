package org.example.td23_spring;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final List<Student> studentList = new ArrayList<>();
    @GetMapping("/welcome")
    public String welcome(@RequestParam String name){
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String addStudent (@RequestBody List<Student> newStudentList){
        studentList.addAll(newStudentList);
        StringBuilder result = new StringBuilder();
        for (Student student : studentList){
            result.append(student.firstName())
                    .append(" ")
                    .append(student.lastName())
                    .append(", ");
        }
        return result.toString();
    }

    @GetMapping("/students")
    public Object getStudents(@RequestHeader(value = "Accept",defaultValue = "text/plain") String accept){
        if ((accept==null)||accept.contains("text/plain")||accept.equals("*/*")){
            StringBuilder result = new StringBuilder();
            for (Student student : studentList){
                result.append(student.firstName())
                        .append(" ")
                        .append(student.lastName())
                        .append("\n ");
            }
            return result.toString();
        }else{return "format not supported";
        }
    }
}
