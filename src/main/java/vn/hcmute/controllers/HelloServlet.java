package vn.hcmute.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello", "/xin-chao"})
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String name = "";

        Cookie[] cookie = req.getCookies();
        if (cookie != null) {
            for (Cookie c : cookie) {
                if ("username".equals(c.getName())) {
                    name = c.getValue();
                }
            }
        }

        if (name == null || name.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cookie-login");
            return;
        }

        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<head><meta charset='UTF-8'><title>Hello Cookie</title></head>");
        printWriter.println("<body>");
        printWriter.println("<h2>Xin chao " + name + "</h2>");
        printWriter.println("<p>Dang nhap bang Cookie thanh cong! Cookie nay ton tai trong 30 giay.</p>");
        printWriter.println("<p><a href='" + req.getContextPath() + "/cookie-login'>Ve trang dang nhap Cookie</a> | <a href='" + req.getContextPath() + "/home'>Ve trang chu</a></p>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
