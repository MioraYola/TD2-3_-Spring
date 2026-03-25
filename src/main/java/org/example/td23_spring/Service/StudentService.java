package org.example.td23_spring.Service;

import org.example.td23_spring.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents){
        students.addAll(newStudents);
        return students;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public String convertStudentsToText(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append("Référence: ").append(s.reference())
                    .append(", Prénom: ").append(s.firstName())
                    .append(", Nom: ").append(s.lastName())
                    .append(", Âge: ").append(s.age())
                    .append(" ans\n");
        }
        return sb.toString();
    }

}
