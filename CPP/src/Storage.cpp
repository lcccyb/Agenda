#include "Storage.hpp"
#include "Path.hpp" // include `Path` class
#include <fstream>  // include `fstream` class 
#include <algorithm>  // include `for_each` function
#include <iostream>
std::shared_ptr<Storage> Storage::m_instance = nullptr;
Storage::Storage() {
    readFromFile();
    m_dirty = false;
}
Storage::~Storage() {
    sync();
}
 /**
 * Convert string to list
 */
void strToList(std::string t_data, std::list<std::string>& t_list) {
    std::string t_item;
    for_each(t_data.begin(), t_data.end(),
                [&t_list, &t_item](char each) {
        if (each != '\n') {
            t_item += each;
        } else {
            t_list.push_back(t_item);
            t_item = "";
        }
    });
}
 /**
 * Split the string, t_data, and pass it to t_record.
 */
void t_Split(std::string& t_data, std::string& t_record, int& t_startIndex) {
    for (; t_data[t_startIndex] != '\"'; ++t_startIndex) {
        t_record += t_data[t_startIndex];
    }
}
bool Storage::readFromFile() {
    std::fstream t_userFile(Path::userPath, std::ios::in);
    std::fstream t_meetingFile(Path::meetingPath, std::ios::in);
    if (t_userFile.is_open() && t_meetingFile.is_open()) {
        std::string t_userData((std::istreambuf_iterator<char>(t_userFile)),
                                std::istreambuf_iterator<char>());
        std::string t_meetingData((std::istreambuf_iterator<char>(t_meetingFile)),
                                std::istreambuf_iterator<char>());
        std::list<std::string> t_userList;
        strToList(t_userData, t_userList);
        std::list<std::string> t_meetingList;
        strToList(t_meetingData, t_meetingList);
        std::string t_name = "", t_password = "", t_email = "", t_phone = "";
        int t_startIndex = 1;
        for (auto t_perUser : t_userList) {
            t_name = t_password = t_email = t_phone = "";
            t_Split(t_perUser, t_name, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perUser, t_password, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perUser, t_email, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perUser, t_phone, t_startIndex);
            t_startIndex = 1;
            createUser(User(t_name, t_password, t_email, t_phone));
        }
        std::string t_sponsor = "", t_participator = "",
                    t_startDate = "", t_endDate = "", t_title = "";
        t_startIndex = 1;
        for (auto t_perMeeting : t_meetingList) {
            t_sponsor = "", t_participator = "",
                    t_startDate = "", t_endDate = "", t_title = "";
            t_Split(t_perMeeting, t_sponsor, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perMeeting, t_participator, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perMeeting, t_startDate, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perMeeting, t_endDate, t_startIndex);
            t_startIndex += 3;
            t_Split(t_perMeeting, t_title, t_startIndex);
            t_startIndex = 1;
            std::string t_perParticipator = "";
            std::vector<std::string> t_participatorVec;
            for_each(t_participator.begin(), t_participator.end(),
                        [&t_participatorVec, &t_perParticipator](char t_perChar) {
                if (t_perChar != '&') {
                    t_perParticipator += t_perChar;
                } else if (t_perParticipator != "") {
                    t_participatorVec.push_back(t_perParticipator);
                    t_perParticipator = "";
                }
            });
            t_participatorVec.push_back(t_perParticipator);
            createMeeting(Meeting(t_sponsor, t_participatorVec,
                            t_startDate, t_endDate, t_title));
        }
        return true;
    }
    return false;
}
bool Storage::writeToFile() {
    std::fstream t_userFile(Path::userPath, std::ios::out);
    std::fstream t_meetingFile(Path::meetingPath, std::ios::out);
    if (t_userFile.is_open() && t_meetingFile.is_open()) {
        for_each(m_userList.begin(), m_userList.end(), [&t_userFile](User t_perUser) {
            t_userFile << "\"" << t_perUser.getName() <<
                "\",\"" << t_perUser.getPassword() << "\",\""
                << t_perUser.getEmail() << "\",\"" <<
                t_perUser.getPhone() << "\"" << std::endl;
        });
        for_each(m_meetingList.begin(), m_meetingList.end(),
                        [&t_meetingFile](Meeting t_perMeeting) {
            t_meetingFile << "\"" << t_perMeeting.getSponsor() << "\",\"";
            int t_first = true;
            for (std::string t_perParticipator :
                        t_perMeeting.getParticipator()) {
                if (t_first && t_perParticipator != "") {
                    t_meetingFile << t_perParticipator;
                    t_first = false;
                } else if (!t_first && t_perParticipator != "") {
                    t_meetingFile << "&" << t_perParticipator;
                    t_first = false;
                }
            }
            t_meetingFile << "\",\"" <<
                Date::dateToString(t_perMeeting.getStartDate())
                << "\",\"" << Date::dateToString(t_perMeeting.getEndDate());
            t_meetingFile << "\",\"" <<
                t_perMeeting.getTitle() << "\"" << std::endl;
        });
        t_userFile.close();
        t_meetingFile.close();
        return true;
    }
    return false;
}

std::shared_ptr<Storage> Storage::getInstance() {
    if (m_instance == nullptr) {
        m_instance = std::shared_ptr<Storage>(new Storage());
    }
    return m_instance;
}
void Storage::createUser(const User& t_user) {
    for (User& t_perUser : m_userList) {
        if (t_perUser.getName() == t_user.getName()) {
            t_perUser = t_user;
            m_dirty = true;
            return;
        }
    }
    m_userList.push_back(t_user);
    sync();
}
std::list<User> Storage::queryUser(
                std::function<bool(const User&)> filter) const {
    std::list<User> t_userList;
    for_each(m_userList.begin(), m_userList.end(),
                [&filter, &t_userList](const User& t_perUser) {
        if (filter(t_perUser)) {
            t_userList.push_back(t_perUser);
        }
    });
    return t_userList;
}
int Storage::updateUser(std::function<bool(const User &)> filter,
                        std::function<void(User&)> switcher) {
    int t_total = 0;
    for_each(m_userList.begin(), m_userList.end(),
                [&filter, &switcher, &t_total](User& t_perUser) {
        if (filter(t_perUser)) {
            switcher(t_perUser);
            t_total++;
        }
    });
    sync();
    return t_total;
}
int Storage::deleteUser(std::function<bool(const User&)> filter) {
    int t_total = 0;
    for (std::list<User>::iterator iter = m_userList.begin();
                    iter != m_userList.end(); ) {
        if (filter(*iter)) {
            iter = m_userList.erase(iter);
            t_total++;
        } else {
            iter++;
        }
    }
    sync();
    return t_total;
}
void Storage::createMeeting(const Meeting& t_meeting) {
    m_meetingList.push_back(t_meeting);
    sync();
}
std::list<Meeting> Storage::queryMeeting(
            std::function<bool(const Meeting&)> filter) const {
    std::list<Meeting> t_meetingList;
    for_each (m_meetingList.begin(), m_meetingList.end(),
            [&filter, &t_meetingList] (const Meeting &t_perMeeting) {
        if (filter(t_perMeeting)) {
            t_meetingList.push_back(t_perMeeting);
        }
    });
    return t_meetingList;
}

int Storage::updateMeeting(std::function<bool(const Meeting &)> filter,
                    std::function<void(Meeting &)> switcher) {
    int t_total = 0;
    for_each(m_meetingList.begin(), m_meetingList.end(),
                [&filter, &switcher, &t_total](Meeting& t_perMeeting) {
        if (filter(t_perMeeting)){
            switcher(t_perMeeting);
            t_total++;
        }
    });
    sync();
    return t_total;
}
int Storage::deleteMeeting(std::function<bool(const Meeting& )> filter) {
    int t_total = 0;
    for (std::list<Meeting>::iterator iter = m_meetingList.begin();
                iter != m_meetingList.end(); ) {
        if (filter(*iter)) {
            iter = m_meetingList.erase(iter);
            t_total++;
        } else {
            iter++;
        }
    }
    sync();
    return t_total;
}
bool Storage::sync() {
    return writeToFile();
}