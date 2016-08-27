import java.io.*;
import java.util.*;
import java.util.regex.*;
public class IO {
	ArrayList<User> m_userList;
	public IO() {
		m_userList = new ArrayList<>();
	}
	public boolean isUser(Filter filter) {
		for (User t_user : m_userList) {
			if (filter.filter(t_user)) {
				return true;
			}
		}
		return false;
	}
	public void switcher(Filter filter) {
		for (User t_user : m_userList) {
			filter.switcher(t_user);
		}
	}
	public static void main(String[] args) {
		IO storage = new IO();
		storage.m_userList.add(new User("yan","1234","yzx9610@outlook.com","13538185906"));
		storage.switcher(new Filter() {
			public boolean filter(final User t_user) {
				return true;
			}
			public void switcher(User t_user) {
				t_user.setName("stary");
			}
		});
		System.out.println(storage.isUser(new Filter() {
			public boolean filter(final User t_user) {
				if (t_user.getName() == "stary") {
					return true;
				}
				return false;
			}
			public void switcher(User t_user) {}
		}));

	}
}

interface Filter {
	boolean filter(final User t_user);
	void switcher(User t_user);
}
