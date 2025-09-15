package com.nhnacademy.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReadCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.getCookie(req, "locale");

        if (Objects.isNull(cookie)) {
            resp.sendError(500, "cookie not found");
            return;
        }

        String locale = cookie.getValue();

        String helloValue = ResourceBundle.getBundle("message", new Locale(locale)).getString("hello");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println(helloValue);
        }

    }
}