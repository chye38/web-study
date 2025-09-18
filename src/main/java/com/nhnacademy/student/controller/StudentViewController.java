package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.*;

@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);

        return "/student/view.jsp";
    }
}
