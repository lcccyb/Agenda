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
public class ChangePhoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AgendaService m_service = new AgendaService();
        String password = request.getParameter("password");
        String phone = request.getParameter("newPhone");
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/Agenda.jsp");
        }
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                userName = each.getValue();
            }
        }
        if (m_service.changePhone(userName, password, phone)) {
            response.sendRedirect("/ChangePhone/ChangePhone_success.jsp");
        } else {
            response.sendRedirect("/ChangePhone/ChangePhone_error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
