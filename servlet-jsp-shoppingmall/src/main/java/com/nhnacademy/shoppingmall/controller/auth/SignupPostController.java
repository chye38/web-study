package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/signupAction.do")
public class SignupPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        String userName = req.getParameter("user_name");
        String userBirth = req.getParameter("user_birth");

        if(Objects.isNull(userId) || Objects.isNull(userPassword) || Objects.isNull(userName) || Objects.isNull(userBirth)){
            log.info("회원가입 입력칸이 비어있음");
            return "redirect:/signup.do";
        }

        try{
            User user = new User(
                    userId,
                    userName,
                    userPassword,
                    userBirth,
                    User.Auth.ROLE_USER,
                    1_000_000,
                    LocalDateTime.now(),
                    null
            );

            userService.saveUser(user);
            log.info("유저 생성 완료");
            return "redirect:/index.do";
        } catch (UserAlreadyExistsException e){
            log.error("UserAlreadyExistsException : {}", e.getMessage());
            return "redirect:/login.do";
        }
    }
}
