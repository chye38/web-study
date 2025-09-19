package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentService;
import com.nhnacademy.student.service.StudentServiceImpl;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentService studentService = new StudentServiceImpl(req);

        List<Student> studentList = studentService.getStudentList();
        req.setAttribute("studentList", studentList);
        return "/student/list.jsp";
    }

}
