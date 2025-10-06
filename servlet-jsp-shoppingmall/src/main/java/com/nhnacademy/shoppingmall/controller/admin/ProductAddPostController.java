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
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/productAddAction.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ProductAddPostController implements BaseController {

     private final ProductService productService = new ProductServiceImpl(new ProductRepositroyImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. 각 폼 필드에 해당하는 Part 객체를 가져옵니다.
            Part productNamePart = req.getPart("productName");
            Part productPricePart = req.getPart("productPrice");
            Part productDescriptionPart = req.getPart("productDescription");

            // 2. Part의 내용을 byte[]로 읽어온 후, UTF-8 문자열로 변환합니다.
            String productName = new String(productNamePart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String productPriceStr = new String(productPricePart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String productDescription = new String(productDescriptionPart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            // 3. 문자열로 변환된 가격을 정수(int)로 파싱합니다.
            int productPrice = Integer.parseInt(productPriceStr);

            Part filePart = req.getPart("productImage");

            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            log.debug("Original File Name: {}", originalFileName);

            // 저장할 경로 구성 (resources 폴더)
            String uploadPath = req.getServletContext().getRealPath("/resources");
            log.debug("Upload Path: {}", uploadPath);

            // 해당 경로가 존재하지 않으면 디렉토리를 생성합니다.
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 4. 파일 저장
            // 실제 저장될 파일 경로를 생성합니다.
            String filePath = uploadPath + File.separator + originalFileName;
            filePart.write(filePath);


            // 5. 데이터베이스에 저장할 이미지 경로 설정
            // 웹에서 접근할 수 있는 상대 경로를 DB에 저장해야 합니다.
            String dbImagePath = "/resources/" + originalFileName;
            log.debug("DB Image Path: {}", dbImagePath);

            // 6. Product 객체 생성 및 서비스 레이어를 통해 DB에 저장
            Product product = new Product(productName, productDescription, productPrice, dbImagePath);
            productService.addProduct(product);

            // 7. 상품 관리 페이지로 리디렉션
            return "redirect:/admin/productManager.do";
        } catch (IOException | ServletException e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}