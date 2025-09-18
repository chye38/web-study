package com.nhnacademy.student.temp;

import static com.nhnacademy.student.util.RequestDispatcher.ERROR_EXCEPTION;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_EXCEPTION_TYPE;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_MESSAGE;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_REQUEST_URI;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_STATUS_CODE;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));

        //todo exception_type
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        //todo message
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        //todo exception
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        //todo request_uri
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        //todo /error.jsp forward 처리
        RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
    }

}