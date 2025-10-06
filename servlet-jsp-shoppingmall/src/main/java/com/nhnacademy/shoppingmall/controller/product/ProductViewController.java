package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositroyImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/productView.do")
public class ProductViewController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositroyImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session =  req.getSession(false);
        User user = null;
        if(Objects.nonNull(session)){
            user = (User)session.getAttribute("loginUser");
        }
        req.setAttribute("user", user);

        Product product = productService.getProduct(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("product", product);

        return "shop/product/productView";
    }
}
