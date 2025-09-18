package com.nhnacademy.student.servlet;

import static com.nhnacademy.student.util.RequestDispatcher.*;

import com.nhnacademy.student.controller.Command;
import com.nhnacademy.student.controller.ControllerFactory;
import com.nhnacademy.student.controller.ErrorController;
import com.nhnacademy.student.controller.StudentDeleteController;
import com.nhnacademy.student.controller.StudentListController;
import com.nhnacademy.student.controller.StudentRegisterController;
import com.nhnacademy.student.controller.StudentRegisterFormController;
import com.nhnacademy.student.controller.StudentUpdateController;
import com.nhnacademy.student.controller.StudentUpdateFormController;
import com.nhnacademy.student.controller.StudentViewController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontSerlvet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";
    private ControllerFactory controllerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException{
        controllerFactory = (ControllerFactory) config.getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            //todo 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
            log.info("path : {}", req.getServletPath());
            Command command = (Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
            String view = command.execute(req,resp);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                //todo `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length() + 1));
//                RequestDispatcher rd = req.getRequestDispatcher(view.substring(REDIRECT_PREFIX.length() + 1));
//                rd.forward(req, resp);
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 처리.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch (Exception ex){
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }

    }

    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        //todo resolveCommand 수정 http-method를 고려

        return command;
    }
}
