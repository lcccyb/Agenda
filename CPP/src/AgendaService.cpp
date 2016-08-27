#include "AgendaService.hpp"
#include "Date.hpp"
#include <iostream>
#include <algorithm>
AgendaService::AgendaService() {
    startAgenda();
}
AgendaService::~AgendaService() {
    quitAgenda();
}
bool AgendaService::userLogIn(const std::string t_userName,
                                const std::string t_password) {
    auto func = [&t_userName, &t_password](const User& t_user)-> bool {
        return (t_user.getName() == t_userName &&
                    t_user.getPassword() == t_password);
    };
    return !m_storage->queryUser(func).empty();
}
bool AgendaService::userRegister(const std::string userName,
                                const std::string password,
                                const std::string email,
                                const std::string phone) {
    auto func = [&userName](const User& t_user) -> bool{
        return t_user.getName() == userName;
    };
    if (!m_storage->queryUser(func).empty()) { return false; }
    m_storage->createUser(User(userName, password, email, phone));
    return true;
}
bool AgendaService::deleteUser(const std::string userName,
                            const std::string password) {
    auto func = [&userName, &password](const User& user)->bool {
        return (user.getName() == userName && user.getPassword() == password);
    };
    if (m_storage->deleteUser(func) != 0) {
        auto func_delMeeting = [&userName](const Meeting& meeting)->bool {
            return (meeting.getSponsor() == userName ||
                    meeting.isParticipator(userName));
        };
        m_storage->deleteMeeting(func_delMeeting);
        return true;
    }
    return false;
}
std::list<User> AgendaService::listAllUsers() const {
    auto func = [](const User& per)->bool {
        return true;
    };
    return m_storage->queryUser(func);
}
bool AgendaService::createMeeting(const std::string userName,
                                    const std::string title,
                                    const std::string startDate,
                                    const std::string endDate,
                       const std::vector<std::string> participator) {
    // check if every participator is unique.
    if (participator.empty()) { return false; }
    if (!participator.empty()) {
        for (int i = 0; i < participator.size() - 1; i++) {
            for (int j = i + 1; j < participator.size(); j++) {
                    if (participator[i] == participator[j]) {
                        return false;
                    }
                }
        }
    }
    // check if the time is correct.
    if (!Date::isValid(Date::stringToDate(startDate))) {
        return false;
    }
    if (!Date::isValid(Date::stringToDate(endDate))) {
        return false;
    }
    if (Date::stringToDate(startDate)
            >= Date::stringToDate(endDate)) { 
        return false;
    }
    // check if the username has been register.
    auto checkSponsor = [&userName](const User& user)->bool {
        return user.getName() == userName;
    };
    if (m_storage->queryUser(checkSponsor).empty()) return false;
    // check if every participator has been registered.
    for (auto &part : participator) {
        auto checkParticipator = [&](const User& user) {
            return user.getName() == part;
        };
        if (m_storage->queryUser(checkParticipator).empty())
            return false;
    }

    auto func = [&userName, &startDate, &endDate, &title](const Meeting& t_meeting) -> bool {
        // check if the title has been used.
        if (title == t_meeting.getTitle()) { return true; }
        // check if the sponsor is vacant.
        if (t_meeting.getSponsor() == userName || t_meeting.isParticipator(userName)) {
            return (!(Date::stringToDate(startDate) >= t_meeting.getEndDate()
                    || Date::stringToDate(endDate) <= t_meeting.getStartDate()));
        }
        return false;
    };
    if (!m_storage->queryMeeting(func).empty()) { return false; }
    // check if every participator is vacant.
    for (std::string t_part : participator) {
        if (t_part == userName) { return false; }
        auto func = [&t_part, &startDate, &endDate](const Meeting& t_meeting) -> bool {
            if (t_meeting.getSponsor() == t_part || t_meeting.isParticipator(t_part)) {
                return (!(Date::stringToDate(startDate) >= t_meeting.getEndDate() ||
                        Date::stringToDate(endDate) <= t_meeting.getStartDate()));
            }
            return false;
        };
        if (!m_storage->queryMeeting(func).empty()) { return false; }
    }
    m_storage->createMeeting(
            Meeting(userName, participator, startDate, endDate, title));
    return true;
}
std::list<Meeting> AgendaService::meetingQuery(const std::string userName,
                                    const std::string title) const {
    auto func = [&userName, &title](const Meeting& t_meeting)->bool {
        if (t_meeting.getTitle() == title) {
            return (t_meeting.getSponsor() == userName ||
                    t_meeting.isParticipator(userName));
        }
        return false;
    };
    return m_storage->queryMeeting(func);
}
std::list<Meeting> AgendaService::meetingQuery(const std::string userName,
                                const std::string startDate,
                                const std::string endDate) const {
    // check if the time is valid.
    if (!(Date::isValid(Date::stringToDate(startDate)) &&
            Date::isValid(Date::stringToDate(endDate)))) {
        return std::list<Meeting>();
    }
    if (Date::stringToDate(startDate) >
            Date::stringToDate(endDate)) { return std::list<Meeting>(); }

    auto func = [&userName, &startDate, &endDate](const Meeting & t_meeting)->bool {
        if (t_meeting.getSponsor() == userName ||
                t_meeting.isParticipator(userName)) {
            return (!(Date::stringToDate(endDate) < t_meeting.getStartDate() || 
                    Date::stringToDate(startDate) > t_meeting.getEndDate()));
        }
        return false;
    };
    return m_storage->queryMeeting(func);
}
std::list<Meeting> AgendaService::listAllMeetings(
                    const std::string userName) const {
    auto func = [&userName](const Meeting& t_meeting)->bool {
        return (t_meeting.getSponsor() == userName ||
                t_meeting.isParticipator(userName));
    };
    return m_storage->queryMeeting(func);
}
std::list<Meeting> AgendaService::listAllSponsorMeetings(
                const std::string userName) const {
    auto func = [&userName](const Meeting& t_meeting)->bool {
        return (t_meeting.getSponsor() == userName);
    };
    return m_storage->queryMeeting(func);
}
std::list<Meeting> AgendaService::listAllParticipateMeetings(
                const std::string userName) const {
    auto func = [&userName](const Meeting& t_meeting)->bool {
        return t_meeting.isParticipator(userName);
    };
    return m_storage->queryMeeting(func);
}
bool AgendaService::deleteMeeting(const std::string userName,
                                    const std::string title) {
    auto func = [&userName, &title](const Meeting& t_meeting)->bool {
        return (t_meeting.getSponsor() == userName &&
                t_meeting.getTitle() == title);
    };
    return m_storage->deleteMeeting(func);
}
bool AgendaService::deleteAllMeetings(const std::string userName) {
    auto func = [&userName](const Meeting& t_meeting)->bool {
        return t_meeting.getSponsor() == userName;
    };
    return m_storage->deleteMeeting(func) != 0;
}
void AgendaService::startAgenda() {
    m_storage = Storage::getInstance();
}
void AgendaService::quitAgenda() {
    m_storage->sync();
}
