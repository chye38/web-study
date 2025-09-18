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
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String findId = req.getParameter("id");
        Student student = studentRepository.getStudentById(findId);
        if(Objects.isNull(student)){
            throw new RuntimeException("Student not found : " + findId);
        }
        req.setAttribute("student", student);
        req.setAttribute("action", "/student/update");

//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = null;
        if(Objects.nonNull(req.getParameter("gender"))){
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))){
            age = Integer.valueOf(req.getParameter("age"));
        }

        invalidData(id, name, gender, age);
        Student student = buildStudent(id, name, gender, age);
        studentRepository.update(student);

//        resp.sendRedirect("/student/view?id=" + id);
        req.setAttribute("view", "redirect:/student/view.do?id=" + id);
    }

    private void invalidData(String id, String name, Gender gender, Integer age){
        if(Objects.isNull(id)){
            throw new RuntimeException("id is null");
        }else if(Objects.isNull(name)){
            throw new RuntimeException("name is null");
        } else if (Objects.isNull(gender)) {
            throw new RuntimeException("gender is null");
        }else if(Objects.isNull(age)){
            throw new RuntimeException("age is null");
        }else if(age < 1){
            throw new RuntimeException("age is greater than zero");
        }
    }

    private Student buildStudent(String id, String name, Gender gender, Integer age){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        return student;
    }
}
