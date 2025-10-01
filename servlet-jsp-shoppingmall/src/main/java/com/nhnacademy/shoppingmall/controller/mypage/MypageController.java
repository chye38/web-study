package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage.do")
public class MypageController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("loginUser");

        if(Objects.isNull(user)){
            return "redirect:/error.do";
        }

        String birthStr = user.getUserBirth();

        req.setAttribute("user", user);
        return "shop/mypage/mypage_form";
    }
}
