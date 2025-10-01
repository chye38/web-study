package com.nhnacademy.shoppingmall.controller.mypage;

import static org.reflections.Reflections.log;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypageAction.do")
public class MypagePostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        String userName = req.getParameter("user_name");
        String userBirth = req.getParameter("user_birth");

        if(Objects.isNull(userId) || Objects.isNull(userPassword) || Objects.isNull(userName) || Objects.isNull(userBirth)){
            log.error("회원수정 입력칸이 비어있음");
            return "redirect:/mypage.do";
        }

        User user = new User(userId, userName, userPassword, userBirth, null, 0, null, null);
        log.info("수정할 정보만 입력 : {}", user);

        userService.updateUser(user);

        return "redirect:/index.do";
    }
}
