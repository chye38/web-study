package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentService;
import com.nhnacademy.student.service.StudentServiceImpl;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        StudentService studentService = new StudentServiceImpl(req);
        studentService.deleteStudent(id);
        return "redirect:/student/list.do";
    }
}
