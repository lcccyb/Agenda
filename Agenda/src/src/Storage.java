/**
 * Created by yanzexin on 16/8/30.
 * All right reserved.
 */
package src;
import java.util.ArrayList;
public class Storage {
    private ArrayList<User> m_userList = new ArrayList<>();
    private ArrayList<Meeting> m_meetingList = new ArrayList<>();
    public static Storage m_Instance = null;
    private Storage() {
        m_userList = DBUtil.readUser();
        m_meetingList = DBUtil.readMeeting();
    }
    public static Storage getInstance() {
        if (m_Instance == null) {
            m_Instance = new Storage();
        }
        return m_Instance;
    }
    public boolean logIn(final String t_userName, final String t_password) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName) && t_user.getPassword().equals(t_password)) {
                return true;
            }
        }
        return false;
    }
    public void createUser(final User t_user) {
        m_userList.add(t_user);
        DBUtil.insertUser(t_user.getName(), t_user.getPassword(), t_user.getEmail(), t_user.getPhone());
    }
    public boolean existUser(final String t_userName) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName)) {
                return true;
            }
        }
        return false;
    }
    public void deleteUser(final String t_userName, final String t_password) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName) && t_user.getPassword().equals(t_password)) {
                m_userList.remove(t_user);
                DBUtil.deleteUser(t_userName, t_password);
                return;
            }
        }
    }
    public boolean changePassword(final String t_userName,
                                  final String oldPassword,
                                  final String newPassword) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName) && t_user.getPassword().equals(oldPassword)) {
                t_user.setPassword(newPassword);
                DBUtil.updateUser("Password", newPassword, t_userName, oldPassword);
                return true;
            }
        }
        return false;
    }
    public boolean changeEmail(final String t_userName,
                               final String t_password,
                               final String newEmail) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName) && t_user.getPassword().equals(t_password)) {
                t_user.setEmail(newEmail);
                DBUtil.updateUser("Email", newEmail, t_userName, t_password);
                return true;
            }
        }
        return false;
    }
    public boolean changePhone(final String t_userName,
                               final String t_password,
                               final String newPhone) {
        for (User t_user : m_userList) {
            if (t_user.getName().equals(t_userName) && t_user.getPassword().equals(t_password)) {
                t_user.setPhone(newPhone);
                DBUtil.updateUser("Email", newPhone, t_userName, t_password);
                return true;
            }
        }
        return false;
    }
    public ArrayList<User> listAllUsers() {
        return m_userList;
    }
    public void createMeeting(final String t_sponsor, final String t_title,
                              final String t_startDate, final String t_endDate,
                              final ArrayList<String> t_participator) {
        m_meetingList.add(new Meeting(t_sponsor, t_participator, Date.stringToDate(t_startDate), Date.stringToDate(t_endDate), t_title));
        DBUtil.insertMeeting(t_sponsor, t_participator, t_startDate, t_endDate, t_title);
    }
    public ArrayList<Meeting> queryMeeting(MeetingFilter filter) {
        ArrayList<Meeting> m_meetingList = new ArrayList<>();
        for (Meeting t_meeting : m_meetingList) {
            if (filter.filter(t_meeting)) {
                m_meetingList.add(t_meeting);
            }
        }
        return m_meetingList;
    }
    public void deleteMeeting(final String t_userName, final String t_title) {
        String sql = "DELETE FROM Meetings WHERE Sponsor = \"" + t_userName + "\" AND Title = \"" + t_title + "\"";
        m_meetingList.stream().filter(t_meeting ->
                t_meeting.getSponsor().equals(t_userName) &&
                        t_meeting.getTitle().equals(t_title)).forEach(t_meeting -> m_meetingList.remove(t_meeting));
        DBUtil.deleteUser(t_userName, t_title);
    }
    public static void main(String[] args) {
    }
}