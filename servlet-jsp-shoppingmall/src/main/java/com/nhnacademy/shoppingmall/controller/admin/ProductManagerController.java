package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositroyImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productManager.do")
public class ProductManagerController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositroyImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        User user = (User) req.getSession(false).getAttribute("loginUser");

        if(Objects.isNull(user)){
            return "redirect:/error.do";
        }

        req.setAttribute("user", user);

        try {
            // 현재 페이지 번호 (기본 1)
            int currentPage = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");

            // 한 페이지에 보여줄 상품 개수
            int pageSize = 10; // 관리 페이지니까 10개 정도로 지정 (필요시 조정)

            // 페이징된 상품 데이터 가져오기
            Page<Product> productPage = productService.getPagedProducts(currentPage, pageSize);

            // JSP에 데이터 세팅
            req.setAttribute("productList", productPage.getContent());
            req.setAttribute("currentPage", currentPage);

            // 총 페이지 수 계산
            long totalPages = (long) Math.ceil((double) productPage.getTotalCount() / pageSize);
            req.setAttribute("totalPages", totalPages);

        } catch (NumberFormatException e) {
            // 파라미터 오류 시 기본 페이지로 처리
            Page<Product> productPage = productService.getPagedProducts(1, 10);
            req.setAttribute("productList", productPage.getContent());
            req.setAttribute("currentPage", 1);
            long totalPages = (long) Math.ceil((double) productPage.getTotalCount() / 10);
            req.setAttribute("totalPages", totalPages);
        }


        return "/shop/admin/productManager";
    }
}
