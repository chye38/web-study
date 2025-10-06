package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productAdd.do")
public class ProductAddController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("loginUser");

        if(Objects.isNull(user)){
            return "redirect:/error.do";
        }

        req.setAttribute("user", user);
        return "/shop/admin/productAdd_Form";
    }
}
