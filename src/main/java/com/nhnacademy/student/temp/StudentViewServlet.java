package com.nhnacademy.student.temp;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
@Slf4j
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String findId = req.getParameter("id");

        // parameter id가 없으면 404
        if (findId == null){
            log.error("찾을 ID가 없습니다 : {}", findId);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "페이지를 찾을 수 없습니다.");
            return;
        }

        Student student = studentRepository.getStudentById(findId);
        req.setAttribute("student", student);

//        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/view.jsp");
    }
}
