package Servlet;

import src.AgendaService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yanzexin on 16/9/1.
 * All right reserved.
 */
public class CreateMeetingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Sponsor = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/Agenda.jsp");
        }
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                Sponsor = each.getValue();
            }
        }
        String title = request.getParameter("title");
        String t_startDate = request.getParameter("startDate");
        String t_endDate = request.getParameter("endDate");
        String t_participator = request.getParameter("participator");
        ArrayList<String> participator = new ArrayList<>();
        String[] split = t_participator.split(",");
        for (String each : split) {
            participator.add(each);
        }
        AgendaService m_service = new AgendaService();
        if (m_service.createMeeting(Sponsor, title, t_startDate, t_endDate, participator)) {
            response.sendRedirect("/CreateMeeting/CreateMeeting_success.jsp");
        } else {
            response.sendRedirect("/CreateMeeting/CreateMeeting_error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
