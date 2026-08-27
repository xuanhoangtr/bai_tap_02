package vn.hcmute.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/session-login"})
public class SessionLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/session_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("xuan".equals(username) && "123".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("name", username);
            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            req.setAttribute("errorMsg", "Tai khoan hoac mat khau khong chinh xac");
            req.getRequestDispatcher("/views/session_login.jsp").forward(req, resp);
        }
    }
}
