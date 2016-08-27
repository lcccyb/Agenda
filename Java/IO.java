import java.io.*;
import java.util.*;
import java.util.regex.*;
public class IO {
	// ArrayList<User> m_userList;
	// public IO() {
	// 	m_userList = new ArrayList<>();
	// }
	// public boolean isUser(Filter filter) {
	// 	for (User t_user : m_userList) {
	// 		if (filter.filter(t_user)) {
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }
	// public void switcher(Filter<> filter) {
	// 	for (User t_user : m_userList) {
	// 		filter.switcher(t_user);
	// 	}
	// }
	public static boolean isNumber(int value, Filter<Integer> func) {
		if (func.filter(value)) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		boolean ans = IO.isNumber(10, new Filter<Integer>() {
			public boolean filter(final Integer objects) {
				return objects > 20;
			}
			public void switcher(Integer objects) {
			}
		});
		System.out.println(ans);
	}
}
