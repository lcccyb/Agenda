public class User {
	private String m_name;
	private String m_password;
	private String m_email;
	private String m_phone;
	public User() {
		m_name = m_password = m_email = m_phone = "";
	}
 	public User(final String t_username, final String t_password, final String t_email, final String t_phone) {
		m_name = t_username;
		m_password = t_password;
		m_email = t_email;
		m_phone = t_phone;
	}
	public User(final User t_user) {
		m_name = t_user.getName();
		m_password = t_user.getPassword();
		m_email = t_user.getEmail();
		m_phone = t_user.getPhone();
	}
	final String getName() {
		return m_name;
	}
	void setName(final String t_name) {
		m_name = t_name;
	}
	final String getPassword() {
		return m_password;
	}
	void setPassword(final String t_password) {
		m_password = t_password;
	}
	final String getPhone() {
		return m_phone;
	}
	void setPhone(final String t_phone) {
		m_phone = t_phone;
	}
	final String getEmail() {
		return m_email;
	}
	void setEmail(final String t_email) {
		m_email = t_email;
	}
	public String toString() {
		return new String("\"" + this.getName() + "\",\"" +
			this.getPassword() + "\",\"" + this.getEmail() + "\",\"" + this.getPhone() + "\"");
	}
	public static void main(String[] args) {
		User user = new User("stary", "123456", "yzx9610@outlook.com", "1234556");
		System.out.println(user.getPhone());
	}
}
