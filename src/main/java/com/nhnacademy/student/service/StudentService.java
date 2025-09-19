package com.nhnacademy.student.service;

import com.nhnacademy.student.domain.Student;
import java.util.List;

public interface StudentService {
    void registerStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String id);
    Student viewStudent(String id);
    List<Student> getStudentList();
}
