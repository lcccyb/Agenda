import java.util.function.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;
public class ExStorage {
    private static ExStorage m_Instance = null;
    public LinkedList<User> m_userList;
    private LinkedList<Meeting> m_meetingList;
    private User Parse(String t_user) {
        Pattern pattern = Pattern.compile("\",\"");
        Matcher matcher = pattern.matcher(t_user);
        int[] index = {0, 0, 0}; int i = 0; int end = t_user.length();
        while (matcher.find()) {
            index[i++] = matcher.start();
        }
        return new User(t_user.substring(1, index[0]),
            t_user.substring(index[0] + 3, index[1]),
            t_user.substring(index[1] + 3, index[2]),
            t_user.substring(index[2] + 3, end));
    }
    public void readFromFile() throws IOException {
        File userFile = new File("./UserData.csv");
        File meetingFile = new File("./MeetingData.csv");
        if (userFile.canRead()) {
            FileInputStream fip = new FileInputStream(userFile);
            Scanner scanner = new Scanner(fip, "UTF-8");
            while (scanner.hasNextLine()) {
                m_userList.add(Parse(scanner.nextLine()));
            }
            fip.close();
        }
        if (meetingFile.canRead()) {
            FileInputStream fip = new FileInputStream(meetingFile);
            Scanner scanner = new Scanner(fip, "UTF-8");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            fip.close();
        }
    }
    public void writeToFile() throws IOException {
        File userFile = new File("/Users/yanzexin/Desktop/Agenda/Java/UserData.csv");
        File meetingFile = new File("/Users/yanzexin/Desktop/Agenda/Java/MeetingData.csv");
        if (userFile.canWrite()) {
            FileOutputStream fop = new FileOutputStream(userFile);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            for (User user : m_userList) {
                writer.append(user + "");
                writer.append("\n");
            }
            writer.close();
            fop.close();
        }
        if (meetingFile.canWrite()) {
            FileOutputStream fop = new FileOutputStream(meetingFile);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            for (Meeting meeting : m_meetingList) {
                writer.append(meeting + "");
                writer.append("\n");
            }
            writer.close();
            fop.close();
        }
    }
    private ExStorage() {
        m_userList = new LinkedList<>();
        m_meetingList = new LinkedList<>();
        sync();
    }
    public static ExStorage getInstance() {
        if (m_Instance == null) {
            m_Instance = new ExStorage();
        }
        return m_Instance;
    }
    public void createUser(final User t_user) {
        m_userList.add(t_user);
        sync();
    }
    public final LinkedList<User> queryUser(UserFilter t_filter) {
        LinkedList<User> t_userList = new LinkedList<>();
        for (User t_user : m_userList) {
            if (t_filter.filter(t_user)) {
                t_userList.add(t_user);
            }
        }
        return t_userList;
    }
    public int updateUser(UserFilter t_filter) {
        int total = 0;
        for (User t_user : m_userList) {
            if (t_filter.filter(t_user)) {
                t_filter.switcher(t_user);
                total++;
            }
        }
        sync();
        return total;
    }
    public int deleteUser(UserFilter t_filter) {
        int total = 0;
        for (User t_user : m_userList) {
            if (t_filter.filter(t_user)) {
                total++;
                m_userList.remove(t_user);
            }
        }
        sync();
        return total;
    }
    public boolean userExisted(final String t_userName) {
        for (User t_user : m_userList) {
            if (t_user.getName() == t_userName) {
                return true;
            }
        }
        return false;
    }
    public void createMeeting(final Meeting t_meeting) {
        m_meetingList.add(t_meeting);
        sync();
    }
    public LinkedList<Meeting> queryMeeting(MeetingFilter t_filter) {
        LinkedList<Meeting> t_meetingList = new LinkedList<>();
        for (Meeting t_meeting : m_meetingList) {
            if (t_filter.filter(t_meeting)) {
                t_meetingList.add(t_meeting);
            }
        }
        sync();
        return t_meetingList;
    }
    public int updateMeeting(MeetingFilter t_filter) {
        int total = 0;
        for (Meeting t_meeting : m_meetingList) {
            if (t_filter.filter(t_meeting)) {
                t_filter.switcher(t_meeting);
            }
        }
        sync();
        return total;
    }
    public int deleteMeeting(MeetingFilter t_filter) {
        int total = 0;
        for (Meeting t_meeting : m_meetingList) {
            if (t_filter.filter(t_meeting)) {
                total++;
                m_meetingList.remove(t_meeting);
            }
        }
        sync();
        return total;
    }
    public boolean sync() {
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        ExStorage ExStorage = ExStorage.getInstance();
        try {
            ExStorage.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user : ExStorage.m_userList) {
            System.out.println(String.valueOf(user));
        }
    }
}
