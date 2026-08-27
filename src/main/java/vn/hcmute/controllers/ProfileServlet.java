package vn.hcmute.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta charset='UTF-8'><title>Profile</title></head>");
            out.println("<body>");
            out.println("<h2>Chao ban, " + name + " den voi trang quan ly tai khoan</h2>");
            out.println("<p><a href='" + req.getContextPath() + "/logout'>Dang xuat</a> | <a href='" + req.getContextPath() + "/home'>Ve trang chu</a></p>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta charset='UTF-8'><title>Chua dang nhap</title></head>");
            out.println("<body>");
            out.println("<h3 style='color:red;'>Xin vui long dang nhap</h3>");
            out.println("<p><a href='" + req.getContextPath() + "/session-login'>Den trang Dang Nhap Session</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
