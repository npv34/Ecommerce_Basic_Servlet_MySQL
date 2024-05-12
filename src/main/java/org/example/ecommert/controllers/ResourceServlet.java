package org.example.ecommert.controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "resourceServlet", urlPatterns = {"/css/*", "/js/*"})
public class ResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getRequestURI();
        InputStream inputStream = getServletContext().getResourceAsStream(filePath);
        try {
            // Thiết lập kiểu MIME tùy thuộc vào loại tệp
            String mimeType = getServletContext().getMimeType(filePath);
            if (mimeType != null) {
                resp.setContentType(mimeType);
            }

            // Gửi nội dung của file CSS đến HttpServletResponse
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, bytesRead);
            }
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } finally {
            // Đóng InputStream sau khi sử dụng xong
            inputStream.close();
        }
    }
}

