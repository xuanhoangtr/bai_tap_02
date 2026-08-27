package vn.hcmute.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.utils.Constant;

@WebServlet(urlPatterns = "/image")
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(Constant.DIR + "/" + fileName);
            resp.setContentType("image/jpeg");
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    IOUtils.copy(fis, resp.getOutputStream());
                }
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
