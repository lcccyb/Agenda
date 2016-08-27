#include "User.hpp"
User::User(std::string t_userName, std::string t_userPassword,
       std::string t_email, std::string t_phone) : m_name(t_userName), m_password(t_userPassword), m_email(t_email), m_phone(t_phone) {
}
User::User(const User& t_user) {
	this->m_name = t_user.getName();
	this->m_password = t_user.getPassword();
	this->m_email = t_user.getEmail();
	this->m_phone = t_user.getPhone();
}
std::string User::getName() const {
	return m_name;
}
std::string User::getPassword() const {
	return m_password;
}
std::string User::getEmail() const {
	return m_email;
}
std::string User::getPhone() const {
	return m_phone;
}
void User::setName(std::string t_name) {
	this->m_name = t_name;
}
void User::setPassword(std::string t_password) {
	this->m_password = t_password;
}
void User::setEmail(std::string t_Email) {
	this->m_email = t_Email;
}
void User::setPhone(std::string t_phone) {
	this->m_phone = t_phone;
}
