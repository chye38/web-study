package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositroyImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/productUpdateAction.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ProductUpdatePostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositroyImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. 기본 정보 받아오기
            int productId = Integer.parseInt(req.getParameter("productId"));

            Part productNamePart = req.getPart("productName");
            Part productPricePart = req.getPart("productPrice");
            Part productDescriptionPart = req.getPart("productDescription");

            String productName = new String(productNamePart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String productPriceStr = new String(productPricePart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String productDescription = new String(productDescriptionPart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            int productPrice = Integer.parseInt(productPriceStr);

            // 기존 상품 정보 가져오기 (기존 이미지 path 유지용)
            Product oldProduct = productService.getProduct(productId);

            // 2. 파일 파트 (선택적)
            Part filePart = req.getPart("productImage");
            String dbImagePath = oldProduct.getPath(); // 기본값: 기존 이미지 경로

            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                log.debug("New Image File: {}", originalFileName);

                // 업로드 경로 (resources 폴더)
                String uploadPath = req.getServletContext().getRealPath("/resources");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + originalFileName;
                filePart.write(filePath);

                dbImagePath = "/resources/" + originalFileName; // 새 이미지 경로로 교체
            }

            // 3. 상품 객체 생성 후 업데이트
            Product updatedProduct = new Product(productId, productName, productDescription, productPrice, dbImagePath);
            productService.updateProduct(updatedProduct);

            return "redirect:/admin/productManager.do";
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException("상품 수정 중 오류 발생", e);
        }
    }
}