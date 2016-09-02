package Servlet;

import src.AgendaService;
import src.Meeting;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yanzexin on 16/9/2.
 * All right reserved.
 */
public class QueryMeetingbyIntervalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                userName = each.getValue();
            }
        }
        if (userName == null) {
            response.sendRedirect("/Agenda.jsp");
        }
        String title = request.getParameter("title");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        AgendaService m_service = new AgendaService();
        ArrayList<Meeting> list = m_service.queryMeetingByInterval(userName, startDate, endDate);
        request.getSession().setAttribute("list", list);
        response.sendRedirect("/QueryMeeting/QueryMeetingbyIntervalShow.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
