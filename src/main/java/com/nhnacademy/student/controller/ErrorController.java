package com.nhnacademy.student.controller;

import static com.nhnacademy.student.util.RequestDispatcher.ERROR_EXCEPTION;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_EXCEPTION_TYPE;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_MESSAGE;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_REQUEST_URI;
import static com.nhnacademy.student.util.RequestDispatcher.ERROR_STATUS_CODE;

import com.nhnacademy.student.util.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(value = "/error.do", method = RequestMapping.Method.GET)
public class ErrorController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        //todo exception_type
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        //todo message
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        //todo exception
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        //todo request_uri
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        return "/error.jsp";
    }
}
