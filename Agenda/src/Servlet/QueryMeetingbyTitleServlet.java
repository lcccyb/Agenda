package Servlet;
import src.AgendaService;
import src.Meeting;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yanzexin on 16/9/2.
 * All right reserved.
 */
public class QueryMeetingbyTitleServlet extends HttpServlet {
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
        AgendaService m_service = new AgendaService();
        ArrayList<Meeting> list = m_service.queryMeetingByTitle(userName, title);
        request.getSession().setAttribute("list", list);
        response.sendRedirect("/QueryMeeting/QueryMeetingbyTitleShow.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
