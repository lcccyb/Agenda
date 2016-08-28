import java.util.*;
public class Meeting {
	private String m_title;
	private String m_sponsor;
	private Date m_startDate;
	private Date m_endDate;
	private ArrayList<String> m_participator;

	public Meeting() {
		m_title = m_sponsor = "";
		m_startDate = new Date();
		m_endDate = new Date();
		m_participator = new ArrayList<>();
	}
	public Meeting(String t_sponsor, ArrayList<String> t_participator, Date t_startTime, Date t_endTime, String t_title) {
		m_sponsor = t_sponsor;
		m_participator = t_participator;
		m_startDate = t_startTime;
		m_endDate = t_endTime;
		m_title = t_title;
	}
	public Meeting(final Meeting t_meeting) {
		this.m_title = t_meeting.getTitle();
		this.m_sponsor = t_meeting.getSponsor();
		this.m_startDate = t_meeting.getStartDate();
		this.m_endDate = t_meeting.getEndDate();
		this.m_participator = t_meeting.getParticipator();
	}
	public final String getSponsor() {
		return m_sponsor;
	}
	public void setSponsor(final String t_sponsor) {
		m_sponsor = t_sponsor;
	}
	public final String getTitle() {
		return m_title;
	}
	public void setTitle(final String t_title) {
		m_title = t_title;
	}
	public final Date getStartDate() {
		return m_startDate;
	}
	public void setStartDate(final Date t_startTime) {
		m_startDate = t_startTime;
	}
	public final Date getEndDate() {
		return m_endDate;
	}
	public void setEndDate(final Date t_endTime) {
		m_endDate = t_endTime;
	}
	public final ArrayList<String> getParticipator() {
		return m_participator;
	}
	public void setParticipator(final ArrayList<String> t_participator) {
		m_participator = t_participator;
	}
	public boolean isParticipator(final String t_username) {
		for (String t_participator : m_participator) {
			if (t_participator.equals(t_username)) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		return new String("\"" + this.getTitle() + "\",\"" + this.getSponsor() +
			"\",\"" + this.getStartDate() + "\",\"" + this.getEndDate() + "\",\"" +
				this.getParticipator()) + "\"";
	}
	public static void main(String[] args) {
		ArrayList<String> t_participator = new ArrayList<>(Arrays.asList("yan", "ze"));
		Date startDate = new Date("2008-10-02/00:30");
		Date endDate = new Date("2009-10-03/00:40");
		Meeting meeting = new Meeting("stayr", t_participator, startDate, endDate, "test");
	}
}