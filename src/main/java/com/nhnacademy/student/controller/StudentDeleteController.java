package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        studentRepository.deleteById(id);
        return "redirect:/student/list.do";
    }
}
