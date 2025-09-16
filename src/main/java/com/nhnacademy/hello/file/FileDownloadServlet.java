package com.nhnacademy.hello.file;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "FileDownloadServlet", urlPatterns = "/file/fileDownload")
@Slf4j
public class FileDownloadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "/Users/chosun-nhn47/Desktop/webApplication/hello/src/main/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        if(Objects.isNull(fileName) || fileName.isEmpty() ){
            resp.sendError(400,"fileName parameter is needed");
            return ;
        }

        String filePath = UPLOAD_DIR + File.separator + fileName;
        Path path = Path.of(filePath);

        if(!Files.exists(path)){
            log.error("File not found:" + fileName);
            resp.sendError(404,"File not found:" + fileName);
            return;
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = resp.getOutputStream()
        ){

            byte[] buffer = new byte[4096];

            int n;
            while (-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
            }
        } catch (IOException ex) {
            log.error("", ex);
        }

    }
}