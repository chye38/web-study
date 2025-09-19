package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentService;
import com.nhnacademy.student.service.StudentServiceImpl;
import com.nhnacademy.student.util.Invalid;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.POST)
public class StudentRegisterController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentService studentService = new StudentServiceImpl(req);
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        Integer age = Integer.valueOf(req.getParameter("age"));

        Invalid.invalid(id, name, gender, age);

        Student student = new Student(id, name, gender, (int)age);

        studentService.registerStudent(student);
        return "redirect:/student/view.do?id=" + student.getId();
    }
}
