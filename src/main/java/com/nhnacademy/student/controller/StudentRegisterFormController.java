package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentRegisterFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
