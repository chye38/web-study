package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");

        try{
            User user = userService.doLogin(userId, userPassword);

            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            session.setMaxInactiveInterval(60*60);

            log.info("Create Session Success");
            log.info("session ID : {}", session.getId());
            log.info("Session loginUser : {}", session.getAttribute("loginUser"));

            if(user.getUserAuth() == User.Auth.ROLE_ADMIN){
                return "redirect:/index.do";
            }

            return "redirect:/index.do";
        } catch (UserNotFoundException e){
            log.error("UserNotFoundException : {}", e.getMessage());
            return "redirect:/login.do";
        }
    }
}
