package com.nhnacademy.jspday2.servlet;

import com.nhnacademy.jspday2.notice.Notice;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class NoticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        List<Notice> noticeList = new ArrayList<>();
    }
}
