package com.nhnacademy.hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("locale=" + req.getLocale());
            out.println("parameter name=" + req.getParameter("userId"));
            out.println("content type=" + req.getContentType());
            out.println("content length=" + req.getContentLengthLong());
            out.println("method=" + req.getMethod());
            out.println("servlet path=" + req.getServletPath());
            out.println("request uri=" + req.getRequestURI());
            out.println("request url=" + req.getRequestURL());
            out.println("User-Agent header=" + req.getHeader("User-Agent"));
        } catch (Exception e) {
            log.error("/req : {}", e.getMessage(), e);
        }
    }
}
