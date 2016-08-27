#include "Meeting.hpp"
#include <algorithm> // include `find` function.
Meeting::Meeting(std::string t_sponsor, std::vector<std::string> t_participator, Date t_startTime,
          Date t_endTime, std::string t_title) : m_sponsor(t_sponsor), m_participators(t_participator), m_startDate(t_startTime), m_endDate(t_endTime), m_title(t_title) {
}
Meeting::Meeting(const Meeting& t_meeting) {
	this->m_sponsor = t_meeting.getSponsor();
	this->m_participators = t_meeting.getParticipator();
	this->m_startDate = t_meeting.getStartDate();
	this->m_endDate = t_meeting.getEndDate();
	this->m_title = t_meeting.getTitle();
}

std::string Meeting::getSponsor() const {
	return m_sponsor;
}
std::vector<std::string> Meeting::getParticipator() const {
	return m_participators;
}
Date Meeting::getStartDate() const {
	return m_startDate;
}
Date Meeting::getEndDate() const {
	return m_endDate;
}
std::string Meeting::getTitle() const {
	return m_title;
}
void Meeting::setSponsor(const std::string t_sponsor) {
	this->m_sponsor = t_sponsor;
}
void Meeting::setParticipator(const std::vector<std::string> t_participators) {
	this->m_participators = t_participators;
}
void Meeting::setStartDate(const Date t_startTime) {
	this->m_startDate = t_startTime;
}
void Meeting::setEndDate(const Date t_endTime) {
	this->m_endDate = t_endTime;
}
void Meeting::setTitle(const std::string t_title) {
	this->m_title = t_title;
}
bool Meeting::isParticipator(const std::string t_username) const {
	if (find(m_participators.begin(), m_participators.end(), t_username) != m_participators.end()) {
		return true;
	}
	return false;
}