package com.nhnacademy.student.temp;

import com.nhnacademy.student.domain.Gender;
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

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "/student/register");
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender").equalsIgnoreCase("M") ? Gender.M : Gender.F;
        int age = Integer.parseInt(req.getParameter("age"));

        boolean invalid = invalidData(id, name, gender, age);
        Student student = buildStudent(id, name, gender, age);
        studentRepository.save(student);

//        resp.sendRedirect("/student/view?id=" + id);
        req.setAttribute("view", "redirect:/student/view.do?id=" + id);
    }

    private boolean invalidData(String id, String name, Gender gender, int age){
        if(id.isEmpty() || id == null){
            log.error("아이디 칸이 비어있습니다");
            throw new IllegalArgumentException("id is null");
        }else if(name.isEmpty() || name == null){
            log.error("이름 칸이 비어있습니다");
            throw new IllegalArgumentException("name is null");
        } else if (gender == null) {
            log.error("성별을 선택해주세요");
            throw new IllegalArgumentException("gender is null");
        }else if(age < 1){
            log.error("나이는 0 이상이어야 합니다");
            throw new IllegalArgumentException("age is greater than zero");
        }
        return true;
    }

    private Student buildStudent(String id, String name, Gender gender, int age){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        return student;
    }
}
