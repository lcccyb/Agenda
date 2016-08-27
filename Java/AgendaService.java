import java.util.*;
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
			public void switcher(User t_user) {
			}
		}).size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean userRegister(final String t_userName, final String t_password, final String t_email, final String t_phone) {
		if (m_storage.queryUser(new UserFilter() {
			public boolean filter(final User t_user) {
				if (t_user.getName() == t_userName) {
					return true;
				}
				return false;
			}
			public void switcher(User t_user) {
			}
		}).size() != 0) {
			m_storage.createUser(new User(t_userName, t_password, t_email, t_phone));
			return true;
		}
		return false;
	}
	public boolean deleteUser(final String t_userName, final String t_password) {
		if (m_storage.deleteUser(new UserFilter() {
			public boolean filter(final User t_user) {
				if (t_user.getName() == t_userName && t_user.getPassword() == t_password) {
					return true;
				}
				return false;
			}
			public void switcher(User t_user) {
			}
		}) != 0) {
			return true;
		}
		return false;
	}
	public final LinkedList<User> listAllUsers() {
		return m_storage.queryUser(new UserFilter() {
			public boolean filter(final User t_user) {
				return true;
			}
			public void switcher(User t_user) {
			}
		});
	}
	public boolean createMeeting(final String t_Sponsor, final String t_title, final String t_StartDate, final String t_EndDate, final ArrayList<String> t_participator) {
		return true;
	}
	public final LinkedList<Meeting> queryMeeting(final String t_userName, final String t_title) {
		return m_storage.queryMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getTitle() == t_title) {
					if (t_meeting.getSponsor() == t_userName || t_meeting.isParticipator(t_userName)) {
						return true;
					}
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		});
	}
	public final LinkedList<Meeting> meetingQuery(final String t_userName, final String t_StartDate, final String t_EndDate) {
		if (!(Date.isValid(new Date(t_StartDate)) && Date.isValid(new Date(t_EndDate)))) {
			return new LinkedList<Meeting>();
		}
		if (Date.stringToDate(t_StartDate).compare(Date.stringToDate(t_EndDate))) {
			return new LinkedList<Meeting>();
		}
		return m_storage.queryMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getSponsor() == t_userName || t_meeting.isParticipator(t_userName)) {
					return (!(Date.stringToDate(t_EndDate).compare(t_meeting.getStartDate())|| t_meeting.getEndDate().compare(Date.stringToDate(t_StartDate))));
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		});
	}
	public final LinkedList<Meeting> listAllMeetings(final String t_userName) {
		return m_storage.queryMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getSponsor() == t_userName || t_meeting.isParticipator(t_userName)) {
					return true;
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		});
	}
	public final LinkedList<Meeting> listAllSponsorMeetings(final String t_userName) {
		return m_storage.queryMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getSponsor() == t_userName) {
					return true;
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		});
	}
	public final LinkedList<Meeting> listAllParticipatorMeetings(final String t_userName) {
		return m_storage.queryMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.isParticipator(t_userName)) {
					return true;
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		});
	}
	public boolean deleteMeeting(final String t_userName, final String t_title) {
		return m_storage.deleteMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getSponsor() == t_userName && t_meeting.getTitle() == t_title) {
					return true;
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {
			}
		}) != 0;
	}
	public boolean deleteAllMeetings(final String t_userName) {
		return m_storage.deleteMeeting(new MeetingFilter() {
			public boolean filter(final Meeting t_meeting) {
				if (t_meeting.getSponsor() == t_userName) {
					return true;
				}
				return false;
			}
			public void switcher(Meeting t_meeting) {}
		}) != 0;
	}
	public void startAgenda() {
		m_storage = Storage.getInstance();
	}
	public void quitAgenda() {
		m_storage.sync();
	}
	public static void main(String[] args) {
	}
}
