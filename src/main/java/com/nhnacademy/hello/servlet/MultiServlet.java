package com.nhnacademy.hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "MultiServlet", urlPatterns = "/multi")
@Slf4j
public class MultiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("java");
        String url = getServletContext().getInitParameter("url");
        try (PrintWriter out = resp.getWriter()) {
            out.println(String.join(", ", values));
            out.printf("url:%jakarta.servlet.ServletContainerInitializer\n", url);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }
}
