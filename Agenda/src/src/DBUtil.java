package src;

/**
 * Created by yanzexin on 16/8/30.
 * All right reserved.
 */
import Json.JSONArray;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DBUtil {
    private static String driverName = DBInfo.driverName;
    private static String dbURL = DBInfo.dbURL;
    private static String userName = DBInfo.userName;
    private static String userPwd = DBInfo.userPwd;
    private static Connection agendaConnection = null;
    private static void openDB() {
        try {
            Class.forName(driverName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Load failed!");
        }
        try {
            agendaConnection = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connecting failed!");
        }
    }
    private static void closeDB() {
        try {
            agendaConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void insertUser(final String t_userName, final String t_password, final String t_email, final String t_phone) {
        try {
            DBUtil.openDB();
            PreparedStatement ps = (PreparedStatement) agendaConnection.prepareStatement("INSERT INTO Users VALUE (?,?,?,?)");
            ps.setString(1, t_userName);
            ps.setString(2, t_password);
            ps.setString(3, t_email);
            ps.setString(4, t_phone);
            ps.execute();
            DBUtil.closeDB();
        } catch (Exception e) {
            DBUtil.closeDB();
            e.printStackTrace();
        }
    }
    public static void updateUser(final String field, final String value, final String t_userName, final String t_password) {
        try {
            DBUtil.openDB();
            PreparedStatement ps = (PreparedStatement)agendaConnection.prepareStatement("UPDATE Users SET "+ field +" = ? WHERE UserName = ? AND Password = ?");
            ps.setString(1, value);
            ps.setString(2, t_userName);
            ps.setString(3, t_password);
            ps.execute();
            DBUtil.closeDB();
        } catch (Exception e) {
            DBUtil.closeDB();
            e.printStackTrace();
        }
    }
    public static void deleteUser(final String t_userName, final String t_password) {
        try {
            DBUtil.openDB();
            PreparedStatement ps = (PreparedStatement)agendaConnection.prepareStatement("DELETE FROM Users WHERE UserName = ? AND Password = ?");
            ps.setString(1, t_userName);
            ps.setString(2, t_password);
            ps.execute();
            DBUtil.closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<User> readUser() {
        try {
            DBUtil.openDB();
            ResultSet rs = agendaConnection.prepareStatement("SELECT * FROM Users").executeQuery();
            ArrayList<User> m_userList = new ArrayList<>();
            while (rs.next()) {
                m_userList.add(new User(rs.getString("UserName"), rs.getString("Password"), rs.getString("Email"), rs.getString("Phone")));
            }
            rs.close();
            DBUtil.closeDB();
            return m_userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Meeting> readMeeting() {
        try {
            DBUtil.openDB();
            ResultSet rs = agendaConnection.prepareStatement("SELECT  * FROM Meetings").executeQuery();
            ArrayList<Meeting> m_meetingList = new ArrayList<>();
            while (rs.next()) {
                ArrayList<Object> participator = new JSONArray(rs.getString("Participator")).getMyArrayList();
                ArrayList<String> ans = participator.stream().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
                m_meetingList.add(new Meeting(rs.getString("Sponsor"), ans,
                        Date.stringToDate(rs.getString("StartDate")),
                        Date.stringToDate(rs.getString("EndDate")),
                        rs.getString("Title")));
            }
            return m_meetingList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertMeeting(final String t_sponsor, final ArrayList<String> t_participator, final String t_startDate, final String t_endDate, final String t_title) {
        try {
            DBUtil.openDB();
            PreparedStatement ps = (PreparedStatement)agendaConnection.prepareStatement("INSERT INTO Meetings VALUES (?,?,?,?,?)");
            ps.setString(1, t_sponsor);
            ps.setString(2, t_title);
            ps.setString(3, t_startDate);
            ps.setString(4, t_endDate);
            ps.setString(5, t_participator.toString());
            ps.execute();
            DBUtil.closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteMeeting(final String t_sponsor, final String t_title) {
        try {
            DBUtil.openDB();
            PreparedStatement ps = (PreparedStatement)agendaConnection.prepareStatement("DELETE FROM Meetings WHERE Sponsor = ? AND Title = ?").executeQuery();
            ps.setString(1, t_sponsor);
            ps.setString(2, t_title);
            ps.execute();
            DBUtil.closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
