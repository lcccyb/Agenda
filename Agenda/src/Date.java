import java.util.regex.*;
import java.util.*;
public class  Date {
    private int m_year;
    private int m_month;
    private int m_day;
    private int m_hour;
    private int m_minute;
    private static final int[] Month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static boolean isLeapYear(int t_year) {
        if (t_year % 400 == 0) { return true; }
        if (t_year % 100 != 0 && t_year % 4 == 0) { return true; }
        return false;
    }
    public Date() {
        m_year = m_month = m_day = m_hour = m_minute = 0;
    }
    public Date(int t_year, int t_month, int t_day, int t_hour, int t_minute) {
        m_year = t_year;
        m_month = t_month;
        m_day = t_day;
        m_hour = t_hour;
        m_minute = t_minute;
    }
    public Date(String date) {
        Matcher matcher = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})/(\\d{2}):(\\d{2})").matcher(date);
        if (matcher.find()) {
            m_year = Integer.parseInt(matcher.group(1));
            m_month = Integer.parseInt(matcher.group(2));
            m_day = Integer.parseInt(matcher.group(3));
            m_hour = Integer.parseInt(matcher.group(4));
            m_minute = Integer.parseInt(matcher.group(5));
        } else {
            m_year = m_month = m_day = m_hour = m_minute = 0;
        }
    }
    public final String toString() {
        return dateToString(this);
    }
    public final int getYear() {
        return m_year;
    }
    public void setYear(final int t_year) {
        m_year = t_year;
    }
    public final int getMonth() {
        return m_month;
    }
    public void setMonth(final int t_month) {
        m_month = t_month;
    }
    public final int getDay() {
        return m_day;
    }
    public void setDay(final int t_day) {
        m_day = t_day;
    }
    public final int getHour() {
        return m_hour;
    }
    public void setHour(final int t_hour) {
        m_hour = t_hour;
    }
    public final int getMinute() {
        return m_minute;
    }
    public void setMinute(final int t_minute) {
        m_minute = t_minute;
    }
    public static boolean isValid (final Date t_date) {
        if (t_date.getYear() > 9999 || t_date.getYear() < 1000) { return false; }
        if (t_date.getMonth() > 12 || t_date.getMonth() < 1) { return false; }
        if (isLeapYear(t_date.getYear()) && t_date.getMonth() == 2) {
        if (t_date.getDay() > 29 || t_date.getDay() < 1) { return false; }
            return true;
        }
        if (t_date.getDay() > Month[t_date.getMonth() - 1]
            || t_date.getDay() <= 0) { return false; }
        if (t_date.getHour() >= 24 || t_date.getHour() < 0) { return false; }
        if (t_date.getMinute() >= 60 || t_date.getMinute() < 0) { return false; }
        return true;
    }
    public static Date stringToDate (final String t_dateString) {
        return new Date();
    }
    private static String int2str(final int t_time) {
        if (t_time < 10) {
            return new String("0" + t_time);
        } else {
            return new String(t_time + "");
        }
    }
    public static String dateToString(final Date t_date) {
        if (isValid(t_date)) {
            return new String(t_date.getYear() + "-" + int2str(t_date.getMonth()) + "-" +
                    int2str(t_date.getDay()) + "/" + int2str(t_date.getHour()) + ":" + int2str(t_date.getMinute()));
        }
        return "0000-00-00/00:00";
    }
    /**
    * override operator ==
    */
    public boolean equals (final Date t_date) {
        if (this.getYear() == t_date.getYear() && 
            this.getMonth() == t_date.getMonth() &&
            this.getDay() == t_date.getDay() &&
            this.getHour() == t_date.getHour() &&
            this.getMinute() == t_date.getMinute()) {
            return true;
        } else {
            return false;
        }
    }
    /**
    * override operator >
    */
    public boolean compare (final Date t_date) {
        if (this.m_year > t_date.getYear()) {
            return true;
        } else if (this.m_year == t_date.getYear()) {
            if (this.m_month > t_date.getMonth()) {
                return true;
            } else if (this.m_month == t_date.getMonth()) {
                if (this.m_day > t_date.getDay()) {
                    return true;
                } else if (this.m_day == t_date.getDay()) {
                    if (this.m_hour > t_date.getHour()) {
                        return true;
                    } else if (this.m_hour == t_date.getHour()) {
                        if (this.m_minute > t_date.getMinute()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
    * override operator >=
    */
    public boolean compareOrEqual(final Date t_date) {
        if (this.compare(t_date) || this.equals(t_date)) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        Date then = new Date("2001-12-02/00:30");
        Date now = new Date(Date.dateToString(then));
        System.out.println(then);
        System.out.println(now);
        System.out.println(then.compare(now));
    }
}
