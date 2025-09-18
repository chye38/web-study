package com.nhnacademy.student.listener;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.random.RandomGenerator;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = null;
        try {
            studentRepository = new JsonStudentRepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30
            Student student = new Student();
            student.setId("student" + i);
            student.setName("아카데미" + i);
            student.setGender(RandomGenerator.getDefault().nextInt(0, 2) % 2 == 0 ? Gender.M : Gender.F);
            student.setAge(RandomGenerator.getDefault().nextInt(20, 31));
            studentRepository.save(student);
        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}
