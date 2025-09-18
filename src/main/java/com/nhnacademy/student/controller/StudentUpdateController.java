package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.util.RequestMapping;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.servlet.http.*;
import java.util.Objects;

@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        Integer age = Integer.valueOf(req.getParameter("age"));

        isvalid(id, name, gender, age);

        Student student = new Student(id, name, gender, (int)age);
        studentRepository.update(student);

        return "redirect:/student/view.do?id=" + student.getId();
    }

    private void isvalid(String id, String name, Gender gender, Integer age){
        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id, name, gender, age 다시 확인");
        }
    }
}