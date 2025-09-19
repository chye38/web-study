package com.nhnacademy.student.service;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.util.Invalid;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    public StudentServiceImpl(HttpServletRequest req){
        studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
    }
    @Override
    public void registerStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student viewStudent(String id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.getStudents();
    }
}
