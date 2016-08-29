import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import Json.JSONArray;
public class Storage {
	String driverName = DBInfo.driverName;  
	String dbURL = DBInfo.dbURL;     
	String userName = DBInfo.userName;
	String userPwd = DBInfo.userPwd;
	private Connection agendaConnection = null;
	public static Storage m_Instance = null;

	private Storage() {
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
    private boolean execute(String sql) {
        try {
            agendaConnection.prepareStatement(sql).execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	public static Storage getInstance() {
		if (m_Instance == null) {
			m_Instance = new Storage();
		}
		return m_Instance;
	}
	public void closeDB() {
		try {
			if (agendaConnection != null) {
				agendaConnection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean logIn(final String t_userName, final String t_password) {
		String sql = "SELECT * From Users WHERE UserName = \"" + t_userName + "\" AND Password = \"" + t_password + "\"";
		try {
			ResultSet rs = agendaConnection.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public void createUser(final User t_user) {
		String sql = "INSERT INTO Users (UserName, " +
				"Password, Email, Phone) VALUES(" + t_user + ")";
		execute(sql);
	}
	public void deleteUser(final String t_userName, final String t_password) {
		String sql = "DELETE FROM Users WHERE UserName = \"" +
				t_userName +"\"AND Password = \"" + t_password + "\"";
        execute(sql);
	}
	public boolean changePassword(final String t_userName,
							   final String oldPassword,
							   final String newPassword) {
		String sql = "UPDATE Users SET Password = \"" + newPassword + "\" WHERE " +
				"UserName = \"" + t_userName + "\" AND " + "Password = \""
				+ oldPassword + "\"";
		return execute(sql);
	}
    public boolean changeEmail(final String t_userName,
                            final String t_password,
                            final String newEmail) {
        String sql = "UPDATE Users SET Email = \"" + newEmail + "\" WHERE UserName = \"" + t_userName + "\" AND Password = \"" +
                t_password + "\"";
        return execute(sql);
    }
    public boolean changePhone(final String t_userName,
                               final String t_password,
                               final String t_phone) {
        String sql = "UPDATE Users SET Phone = \"" + t_phone + "\" WHERE UserName = \"" + t_userName + "\" AND Password = \"" +
                t_password + "\"";
        return execute(sql);
    }
	public void createMeeting(final String t_sponsor, final String t_title,
							  final String t_startDate, final String t_endDate,
							  final ArrayList<String> t_participator) {
		JSONArray participator = new JSONArray(t_participator);
		String sql = "INSERT INTO Meetings (Sponsor, Title, StartDate, EndDate, Participator) VALUES (\"" + t_sponsor + "\",\"" +
				t_title + "\",\"" + t_startDate + "\",\"" + t_endDate + "\",\'" + participator + "\')";
		execute(sql);
	}
	public void deleteMeeting(final String t_userName, final String t_title) {
		String sql = "DELETE FROM Meetings WHERE Sponsor = \"" + t_userName + "\" AND Title = \"" + t_title + "\"";
		execute(sql);
	}
	public LinkedList<Meeting> listAllMeeting(final String t_userName) {
		String sql = "SELECT * FROM Meetings";
		LinkedList<Meeting> meetingList = new LinkedList<>();
		try {
			ResultSet rs = agendaConnection.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				ArrayList<Object> participator = new JSONArray(rs.getString("Participator")).getMyArrayList();
				if (rs.getString("Sponsor").equals(t_userName) || participator.contains(t_userName)) {
					ArrayList<String> ans = participator.stream().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
					meetingList.add(new Meeting(rs.getString("Sponsor"),
							ans,
							Date.stringToDate(rs.getString("StartDate")),
							Date.stringToDate(rs.getString("EndDate")),
							rs.getString("Title")));
				}
			}
			return meetingList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LinkedList<>();
	}
	public LinkedList<Meeting> listAllSponsorMeeting(final String t_userName) {
		String sql = "SELECT * FROM Meetings WHERE Sponsor = \"" + t_userName + "\"";
		LinkedList<Meeting> meetingList = new LinkedList<>();
		try {
			ResultSet rs = agendaConnection.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				if (rs.getString("Sponsor").equals(t_userName)) {
					ArrayList<Object> participator = new JSONArray(rs.getString("Participator")).getMyArrayList();
					ArrayList<String> ans = participator.stream().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
					meetingList.add(new Meeting(rs.getString("Sponsor"),
							ans,
							Date.stringToDate(rs.getString("StartDate")),
							Date.stringToDate(rs.getString("EndDate")),
							rs.getString("Title")));
				}
			}
			return meetingList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LinkedList<>();
	}
	public LinkedList<Meeting> listAllParticipatorMeeting(final String t_userName) {
		String sql = "SELECT * FROM Meetings";
		LinkedList<Meeting> meetingList = new LinkedList<>();
		try {
			ResultSet rs = agendaConnection.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				ArrayList<Object> participator = new JSONArray(rs.getString("Participator")).getMyArrayList();
				if (participator.contains(t_userName)) {
					ArrayList<String> ans = participator.stream().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
					meetingList.add(new Meeting(rs.getString("Sponsor"),
							ans,
							Date.stringToDate(rs.getString("StartDate")),
							Date.stringToDate(rs.getString("EndDate")),
							rs.getString("Title")));
				}
			}
			return meetingList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LinkedList<>();
	}
	public static void main(String[] args) {
		Storage m_storage = Storage.getInstance();
		User t_user = new User("stary","1234","718202260@qq.com","13538185906");
		System.out.println(m_storage.logIn("stary", "12345"));
		m_storage.createUser(t_user);
//		m_storage.deleteUser("stary", "12345");
		m_storage.changePassword("stary","1234", "12345");
        m_storage.changeEmail("stary", "12345", "yzx9610@outlook.com");
        m_storage.changePhone("stary","12345","1234566");
		ArrayList<String> array = new ArrayList<>();
		array.add("yan"); array.add("ze");
		m_storage.createMeeting("Stary", "Test", "2016-10-02/00:30", "2016-10-04/00:30", array);
		for (Meeting t_meeting :
				m_storage.listAllMeeting("Stary")) {
			System.out.println(t_meeting);
		}
		m_storage.closeDB();
	}
}