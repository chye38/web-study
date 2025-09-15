package com.nhnacademy.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session 있으면 가져오고 없으면 null
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session)) {
            session.invalidate();
        }

        Cookie cookie =  CookieUtils.getCookie(req,"JSESSIONID");
        if(Objects.nonNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0); // 쿠키의 유효 기간을 0초로 설정. setMaxAge(0)은 브라우저에게 해당 쿠키를 즉시 삭제하라는 의미를 전달
            resp.addCookie(cookie);
        }

        resp.sendRedirect("/login.html");
    }
}