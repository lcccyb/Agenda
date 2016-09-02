package Servlet;
import src.AgendaService;
import src.Date;
import src.Meeting;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yanzexin on 16/9/1.
 * All right reserved.
 */
public class AllMeetingServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] types = request.getParameterValues("type");
        String[] sorts = request.getParameterValues("sort");
        String userName = null;
        Cookie[] cookies = request.getCookies();
        String type = types[0];
        String sort = sorts[0];
        for (Cookie each : cookies) {
            if (each.getName().equals("userName")) {
                userName = each.getValue();
            }
        }
        AgendaService m_service = new AgendaService();
        ArrayList<Meeting> list = null;
        if (type == null) {
            list = m_service.listAllMeetings(userName);
        } else if (type.equals("1")) {
            list = m_service.listAllMeetings(userName);
        } else if (type.equals("2")) {
            list = m_service.listAllParticipatorMeetings(userName);
        }
        if (list != null) {
            if (sort == null) {
                Collections.sort(list, (arg0, arg1) -> Date.dateToString(arg0.getStartDate()).compareTo(Date.dateToString(arg1.getStartDate())));
            } else if (sort.equals("1")) {
                Collections.sort(list, (arg0, arg1) -> Date.dateToString(arg0.getStartDate()).compareTo(Date.dateToString(arg1.getStartDate())));
            } else if (sort.equals("2")) {
                Collections.sort(list, (arg0, arg1) -> Date.dateToString(arg1.getStartDate()).compareTo(Date.dateToString(arg0.getStartDate())));
            }
        }
        request.getSession().setAttribute("list", list);
        response.sendRedirect("/AllSponsorMeeting/AllSponsorMeeting.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
