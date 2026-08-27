package vn.hcmute.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cookie-login"})
public class CookieLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/cookie_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if ("xuan".equals(user) && "123".equals(pass)) {
            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(30);
            cookie.setPath("/");
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/hello");
        } else {
            resp.sendRedirect(req.getContextPath() + "/cookie-login?error=1");
        }
    }
}
