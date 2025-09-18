package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import java.util.List;

public interface StudentRepository {
    void save(Student student);
    void update(Student student);
    void deleteById(String id);
    Student getStudentById(String id);
    List<Student> getStudents();
    boolean existById(String id);
}
