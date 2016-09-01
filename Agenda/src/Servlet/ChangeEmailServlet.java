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
public class ChangeEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("Agenda.jsp");
        }
        String userName = null;
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                userName = each.getValue();
            }
        }
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        AgendaService m_service = new AgendaService();
        if (m_service.changeEmail(userName, password, email)) {
            response.sendRedirect("/ChangeEmail/ChangeEmail_success.jsp");
        } else {
            response.sendRedirect("/ChangeEmail/ChangeEmail_error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
