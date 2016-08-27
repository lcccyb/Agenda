#include "Date.hpp"
#include <sstream>   // include `stringstream` class.
#include <cstdlib>  // include `atoi` function.
 /**
 * Record the number of day of each month.
 */
int Month[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
 /**
 * Judge if the num is a number.
 */
bool isnumber(char num) {
    if (num >= 48 || num <= 57) { return true; }
    return false;
}
 /**
 * Judge if the format correct.
 */
bool FormatCheck(std::string time) {
    if (time.length() != 16) { return false; }
    for (int i = 0; i != 4; i++) {
       if (!isnumber(time[i])) { return false; }
    }
    if (time[4] != '-') { return false; }
    if (!(isnumber(time[5]) && isnumber(time[6]))) {
        return false;
    }
    if (time[7] != '-') { return false; }
    if (!(isnumber(time[8]) && isnumber(time[9]))) {
        return false;
    }
    if (time[10] != '/') { return false; }
    if (!(isnumber(time[11]) && isnumber(time[12]))) {
        return false;
    }
    if (time[13] != ':') { return false; }
    if (!(isnumber(time[14]) && isnumber(time[15]))) {
        return false;
    }
    return true;
}
 /**
 * Judge if the year is leap year.
 */
bool isLeapYear(const int year) {
    if (year % 400 == 0) { return true; }
    if (year % 100 != 0 && year % 4 == 0) { return true; }
    return false;
}
Date::Date() {
    m_year = 0;
    m_month = 0;
    m_day = 0;
    m_hour = 0;
    m_minute = 0;
}
Date::Date(int t_year, int t_month, int t_day, int t_hour, int t_minute) :
 m_year(t_year), m_month(t_month), m_day(t_day), m_hour(t_hour), m_minute(t_minute) {
}
 /**
 * Convert the string to int.
 */
int str2int(std::string& t_dateString, int t_first, int t_last) {
    std::string t_time = t_dateString.substr(t_first, t_last);
    return atoi(t_time.c_str());
}
Date::Date(std::string t_dateString) {
    if (FormatCheck(t_dateString)) {
        m_year = str2int(t_dateString, 0, 4);
        m_month = str2int(t_dateString, 5, 7);
        m_day = str2int(t_dateString, 8, 10);
        m_hour = str2int(t_dateString, 11, 13);
        m_minute = str2int(t_dateString, 14, 16);
    } else {
        m_year = m_month = m_day = m_hour = m_minute = 0;
    }
}
int Date::getYear() const {
    return m_year;
}
void Date::setYear(const int t_year) {
    m_year = t_year;
}
int Date::getMonth() const {
    return m_month;
}
void Date::setMonth(const int t_month) {
    m_month = t_month;
}
int Date::getDay() const {
    return m_day;
}
void Date::setDay(const int t_day) {
    m_day = t_day;
}
int Date::getHour() const {
    return m_hour;
}
void Date::setHour(const int t_hour) {
    m_hour = t_hour;
}
int Date::getMinute() const {
    return m_minute;
}
void Date::setMinute(const int t_minute) {
    m_minute = t_minute;
}
 /**
 * Judge if the date is valid.
 */
bool Date::isValid(const Date t_date) {
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

Date Date::stringToDate(const std::string t_dataString) {
    if (FormatCheck(t_dataString)) {
        return Date(t_dataString);
    }
    return Date();
}
 /**
 * Convert int to string.
 */
std::string Int2Str(const int t_time) {
    std::stringstream t_convertor;
    t_convertor << t_time;
    if (t_time < 10) { return ("0" + t_convertor.str()); }
    return t_convertor.str();
}
std::string Date::dateToString(Date t_date) {
    if (isValid(t_date)) {
        std::string t_dateString = "";
        t_dateString += Int2Str(t_date.getYear());
        t_dateString += "-";
        t_dateString += Int2Str(t_date.getMonth());
        t_dateString += "-";
        t_dateString += Int2Str(t_date.getDay());
        t_dateString += "/";
        t_dateString += Int2Str(t_date.getHour());
        t_dateString += ":";
        t_dateString += Int2Str(t_date.getMinute());
        return t_dateString;
    }
    return "0000-00-00/00:00";
}

Date& Date::operator=(const Date &t_date) {
    this->m_year = t_date.getYear();
    this->m_month = t_date.getMonth();
    this->m_day = t_date.getDay();
    this->m_hour = t_date.getHour();
    this->m_minute = t_date.getMinute();
    return *this;
}
bool Date::operator==(const Date &t_date) const {
    if (this->m_year != t_date.getYear() || 
        this->m_month != t_date.getMonth() || 
        this->m_day != t_date.getDay() || 
        this->m_hour != t_date.getHour() || 
        this->m_minute != t_date.getMinute()) {
        return false;
    }
    return true;
}
bool Date::operator>(const Date& t_date) const {
    if (this->m_year > t_date.getYear()) {
        return true;
    } else if (this->m_year == t_date.getYear()) {
        if (this->m_month > t_date.getMonth()) {
            return true;
        } else if (this->m_month == t_date.getMonth()) {
            if (this->m_day > t_date.getDay()) {
                return true;
            } else if (this->m_day == t_date.getDay()) {
                if (this->m_hour > t_date.getHour()) {
                    return true;
                } else if (this->m_hour == t_date.getHour()) {
                    if (this->m_minute > t_date.getMinute()) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}
bool Date::operator<(const Date& t_date) const {
    if (!(*this > t_date) && !(*this == t_date)) {
        return true;
    }
    return false;
}
bool Date::operator>=(const Date& t_date) const {
    if (*this > t_date || *this == t_date) {
        return true;
    }
    return false;
}
bool Date::operator<=(const Date& t_date) const {
    if (*this < t_date || *this == t_date) {
        return true;
    }
    return false;
}