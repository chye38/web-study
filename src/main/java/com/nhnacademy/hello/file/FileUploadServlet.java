package com.nhnacademy.hello.file;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;


@WebServlet(name = "FileUpload", urlPatterns = "/file/fileUpload")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        //location 위치는 적절히 변경합니다.
        location = "/Users/chosun-nhn47/Desktop/webApplication/hello/src/main/upload/temp"
)
@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/chosun-nhn47/Desktop/webApplication/hello/src/main/upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()){
            // Content-Disposition: form-data; filename="=?UTF-8?Q?=E4=B8=AD=E6=96=87.txt?=";....
            // 리소스(파일 등)의 처리 방법을 지정하는 데 사용되는 헤더. 주로 다운로드되는 파일의 이름 등을 지정하는 데 사용됨
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if (contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);

                if (part.getSize() > 0) {
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("{}={}", part.getName(), formValue);
            }
        }
        resp.sendRedirect("/");
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}