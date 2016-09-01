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
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword_confirm = request.getParameter("newPassword-confirm");
        AgendaService m_service = new AgendaService();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/Agenda.jsp");
        }
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                if (!each.getValue().equals(userName)) {
                    response.sendRedirect("/Agenda.jsp");
                }
            }
        }
        if (!newPassword.equals(newPassword_confirm)) {
            response.sendRedirect("/ChangePassword/ChangePassword_error.jsp");
        }
        if (m_service.changePassword(userName, oldPassword, newPassword)) {
            response.sendRedirect("/ChangePassword/ChangePassword_success.jsp");
        } else {
            response.sendRedirect("ChangePassword/ChangePassword_error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
