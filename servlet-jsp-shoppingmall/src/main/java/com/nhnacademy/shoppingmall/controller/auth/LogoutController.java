package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/logout.do")
public class LogoutController implements BaseController {
    //todo#13-3 로그아웃 구현
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "shop/login/login_form";
    }

}
