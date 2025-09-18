package com.nhnacademy.student.temp;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/list")
public class StudentListServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);
        req.setAttribute("view", "/student/list.jsp");
//        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
//        rd.forward(req, resp); // req.setAttribute (application scope가 요청만 해당)
//        // 요청 데이터를 가지고 url은 그대로지만 rd에 담겨있는 곳으로 전송
    }
}
