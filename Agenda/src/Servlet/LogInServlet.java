package Servlet;

import src.AgendaService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanzexin on 16/9/1.
 * All right reserved.
 */
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        AgendaService m_service = new AgendaService();
        if (m_service.userLogIn(name, password)) {
            Cookie cookie = new Cookie("userName", name);
            response.addCookie(cookie);
            response.sendRedirect("main.jsp");
        } else {
            Cookie cookie = new Cookie("loginFail", "Wrong UserName");
            response.addCookie(cookie);
            response.sendRedirect("LogIn.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
