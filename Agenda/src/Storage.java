import java.sql.*;
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

	public static void main(String[] args) {
		Storage m_storage = Storage.getInstance();
//		User t_user = new User("stary","1234","718202260@qq.com","13538185906");
//		System.out.println(m_storage.logIn("stary", "12345"));
//		m_storage.createUser(t_user);
//		m_storage.deleteUser("stary", "1234");
//		m_storage.changePassword("stary","1234", "12345");
//        m_storage.changeEmail("stary", "12345", "yzx9610@outlook.com");
        m_storage.changePhone("stary","12345","1234566");
		m_storage.closeDB();
	}
}