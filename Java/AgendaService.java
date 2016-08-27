public class AgendaService {
	private Storage m_storage;
	public AgendaService() {
		m_storage = Storage.getInstance();
	}
	public boolean userLogIn(final String t_userName, final String t_password) {
		if (m_storage.queryUser(new UserFilter() {
			public boolean filter(final User t_user) {
				if (t_user.getName() == t_userName && t_user.getPassword() == t_password) {
					return true;
				}
				return false;
			}
			public boolean switcher(User t_user) {
				return true;
			}
		})) {
			
		}
	}
}
