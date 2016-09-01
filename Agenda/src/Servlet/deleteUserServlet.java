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
public class deleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("Password_confirm");
        String confirm_info = request.getParameter("confirmInfo");
        String cancel = request.getParameter("cancel");
        if (cancel.equals("cancel")) {
            response.sendRedirect("main.jsp");
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("Agenda.jsp");
        }
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                if (!each.getValue().equals(userName)) {
                    response.sendRedirect("/DeleteUser/deleteUser_error.jsp");
                }
            }
        }
        AgendaService m_service = new AgendaService();
        if (!password.equals(password_confirm) || !confirm_info.equals("I really wanna delete user!")) {
            response.sendRedirect("/DeleteUser/deleteUser_error.jsp");
            return;
        }
        if (m_service.deleteUser(userName, password)) {
            response.sendRedirect("Agenda.jsp");
        } else {
            response.sendRedirect("/DeleteUser/deleteUser_error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
