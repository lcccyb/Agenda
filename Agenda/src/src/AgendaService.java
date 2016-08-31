/**
 * Created by yanzexin on 16/8/30.
 * All right reserved.
 */
package src;
import java.util.*;

public class AgendaService {
    private Storage m_storage;
    public AgendaService() {
        m_storage = Storage.getInstance();
    }
    public boolean userLogIn(final String t_userName, final String t_password) {
        return m_storage.logIn(t_userName, t_password);
    }
    public boolean userRegister(final String t_userName, final String t_password, final String t_email, final String t_phone) {
        if (m_storage.existUser(t_userName)) {
            return false;
        }
        m_storage.createUser(new User(t_userName, t_password, t_email, t_phone));
        return true;
    }
    public boolean deleteUser(final String t_userName, final String t_password) {
        m_storage.deleteUser(t_userName, t_password);
        return !m_storage.existUser(t_userName);
	}
    public boolean changePassword(final String t_userName, final String old_password, final String new_password) {
        return m_storage.changePassword(t_userName, old_password, new_password);
    }
    public boolean changeEmail(final String t_userName, final String t_password, final String t_email) {
        return m_storage.changeEmail(t_userName, t_password, t_email);
    }
    public boolean changePhone(final String t_userName, final String t_password, final String t_phone) {
        return m_storage.changePhone(t_userName, t_password, t_phone);
    }
    public boolean createMeeting(final String t_Sponsor, final String t_title, final String t_StartDate, final String t_EndDate, final ArrayList<String> t_participator) {
        /**
         * check if exist participator or
         * if every participator is unique.
         */
        if (t_participator.size() == 0) { return false; }
        else if (t_participator.size() != new HashSet<>(t_participator).size()) {
            return false;
        }
        if (!Date.isValid(new Date(t_StartDate))) {
            return false;
        }
        if (!Date.isValid(new Date(t_EndDate))) {
            return false;
        }
        if (Date.stringToDate(t_StartDate).compare(Date.stringToDate(t_EndDate)) ||
            Date.stringToDate(t_StartDate).equals(Date.stringToDate(t_EndDate))) {
            return false;
        }
        if (!m_storage.existUser(t_Sponsor)) {
            return false;
        }
        for (String perParticipator : t_participator) {
            if (!m_storage.existUser(perParticipator)) {
                return false;
            }
        }
        if (m_storage.queryMeeting(t_meeting -> {
            if (t_meeting.getTitle().equals(t_title)) { return true; }
            if (t_meeting.getSponsor().equals(t_Sponsor) || t_meeting.isParticipator(t_Sponsor)) {
                return (!(t_StartDate.compareTo(Date.dateToString(t_meeting.getEndDate())) >= 0 ||
                        Date.dateToString(t_meeting.getStartDate()).compareTo(t_EndDate) >= 0));
            }
            return false;
        }).size() != 0) {
            return false;
        }
        for (String perParticipator : t_participator) {
            if (perParticipator.equals(t_Sponsor)) {
                return false;
            }
            if (m_storage.queryMeeting(t_meeting -> {
                if (t_meeting.getSponsor().equals(perParticipator) || t_meeting.isParticipator(perParticipator)) {
                    return (!(t_StartDate.compareTo(Date.dateToString(t_meeting.getEndDate())) >= 0 ||
                            Date.dateToString(t_meeting.getStartDate()).compareTo(t_EndDate) >= 0));
                }
                return false;
            }).size() != 0) {
                return false;
            }
        }
        m_storage.createMeeting(t_Sponsor,t_title,t_StartDate, t_EndDate, t_participator);
        return true;
    }
	 public final ArrayList<User> listAllUsers() {
        return m_storage.listAllUsers();
	}
    public final ArrayList<Meeting> listAllMeetings(final String t_userName) {
        return m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                return (t_meeting.getSponsor().equals(t_userName) || t_meeting.isParticipator(t_userName));
            }
        });
    }
    public final ArrayList<Meeting> listSponsorMeetings(final String t_userName) {
        return m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                return t_meeting.getSponsor().equals(t_userName);
            }
        });
    }
    public final ArrayList<Meeting> listAllParticipatorMeetings(final String t_userName) {
        return m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                return t_meeting.isParticipator(t_userName);
            }
        });
    }
    public final ArrayList<Meeting> queryMeetingByTitle(final String t_userName, final String t_title){
        return m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                if (t_meeting.getSponsor().equals(t_userName) || t_meeting.isParticipator(t_userName)) {
                    return t_meeting.getTitle().equals(t_title);
                }
                return false;
            }
        });
    }
    public final ArrayList<Meeting> queryMeetingByInterval(final String t_userName, final String t_startDate, final String t_endDate) {
        if (!Date.isValid(Date.stringToDate(t_startDate))) {
            return null;
        }
        if (!Date.isValid(Date.stringToDate(t_endDate))) {
            return null;
        }
        if (t_startDate.compareTo(t_endDate) > 0) {
            return null;
        }
        return m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                if (t_meeting.getSponsor().equals(t_userName) || t_meeting.isParticipator(t_userName)) {
                    return !(t_endDate.compareTo(Date.dateToString(t_meeting.getEndDate())) < 0 ||
                            t_startDate.compareTo(Date.dateToString(t_meeting.getStartDate())) > 0);
                }
                return false;
            }
        });
    }
    public void startAgenda() {
        m_storage = Storage.getInstance();
    }
    public static void main(String[] args) {
        AgendaService m_storage = new AgendaService();
        System.out.print(m_storage.userLogIn("stary", "123"));
    }
}
