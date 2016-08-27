#include "AgendaUI.hpp"
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <iomanip>
using std::setw;
void Head() {
	std::cout << std::endl;
	std::cout << std::endl;
	for (int i = 0; i < 20; ++i) {
		std::cout << "-";
	}
	std::cout << "Agenda";
	for (int i = 0; i < 20; ++i) {
		std::cout << "-";
	}
}
void Root() {
	for (int i = 0; i < 20; ++i) {
		std::cout << "-";
	}
	std::cout << "Agenda";
	for (int i = 0; i < 20; ++i) {
		std::cout << "-";
	}
	std::cout << std::endl;
	std::cout << std::endl;
}
void HelpInfor() {
	Head();
	std::cout << "Action  :" << std::endl;
	std::cout << "l    - log in Agenda by user name and password" << std::endl;
	std::cout << "r    - register and Agenda account" << std::endl;
	std::cout << "q    - quit Agenda" << std::endl;
	Root();
	std::cout << "Agenda : ~$ "; 
}
void LogIn() {
	std::cout << "[log in] [user name] [password]" << std::endl;
	std::cout << "[log in] ";
}
void LogInSuccess() {
	Head();
	std::cout << std::endl;
	std::cout << "Action :" << std::endl;
	std::cout << "o    - log out Agenda" << std::endl;
	std::cout << "dc   - delect Agenda account" << std::endl;
	std::cout << "lu   - list all Agenda user" << std::endl;
	std::cout << "cm   - create a meeting" << std::endl;
	std::cout << "la   - list all meetings" << std::endl;
	std::cout << "las  - list all sponsor meetings" << std::endl;
	std::cout << "lap  - list all participate meetings" << std::endl;
	std::cout << "qm   - query meeting by title" << std::endl;
	std::cout << "qt   - query meeting by time interval" << std::endl;
	std::cout << "dm   - delete meeting by title" << std::endl;
	std::cout << "da   - delete all meetings" << std::endl;
	std::cout << "help - get all choice information" << std::endl;
	Root();
}

AgendaUI::AgendaUI() {
	m_userName = "";
	m_userPassword = "";
	m_agendaService = AgendaService();
}
void AgendaUI::OperationLoop() {
	std::string choice = "Contiune";
	do {
		startAgenda();
		choice = getOperation();
	} while (executeOperation("Root", choice));
}
void AgendaUI::startAgenda() {
	Head();
	std::cout << "\nAction  :" << std::endl;
	std::cout << "l    - log in Agenda by user name and password" << std::endl;
	std::cout << "r    - register and Agenda account" << std::endl;
	std::cout << "q    - quit Agenda" << std::endl;
	Root();
	std::cout << "Agenda : ~$ "; 
}
std::string AgendaUI::getOperation() {
	std::string choice; std::cin >> choice;
	if (choice == "l") { return "LogIn"; }
	if (choice == "r") { return "Register"; }
	if (choice == "q") { return "quit"; }
	if (choice == "o") { return "LogOut"; }
	if (choice == "dc") { return "DeleteAgenda"; }
	if (choice == "lu") { return "ListAgenda"; }
	if (choice == "cm") { return "CreateMeeting"; }
	if (choice == "la") { return "ListMeeting"; }
	if (choice == "las") { return "ListSponsorMeeting"; }
	if (choice == "lap") { return "ListParticipateMeeting"; }
	if (choice == "qm") { return "QueryTitle"; }
	if (choice == "qt") { return "QueryTime"; }
	if (choice == "dm") { return "DeleteMeetingTitle"; }
	if (choice == "da") { return "DeleteMeeting"; }
	if (choice == "help") { return "Help"; }
	return "Contiune";
}
bool AgendaUI::executeOperation(std::string state, std::string t_operation) {
	if (state == "Root") {
		if (t_operation == "quit") { 
			quitAgenda(); return false; }
		if (t_operation == "LogIn") { 
			userLogIn(); return true;}
		if (t_operation == "Register") { 
			userRegister(); return true;}
		if (t_operation == "Contiune") { 
			std::cout << "\nPlease Try Again\n"; Root(); }
	} else if (state == "LogIn") {
		if (t_operation == "LogOut") {
			userLogOut(); return false; }
		if (t_operation == "DeleteAgenda") {
			deleteUser(); return false;}
		if (t_operation == "ListAgenda") { 
			listAllUsers(); return true;}
		if (t_operation == "CreateMeeting") { 
			createMeeting(); return true;}
		if (t_operation == "ListMeeting") { 
			listAllMeetings(); return true;}
		if (t_operation == "ListSponsorMeeting") { 
			listAllSponsorMeetings(); return true;}
		if (t_operation == "ListParticipateMeeting") { 
			listAllParticipateMeetings(); return true;}
		if (t_operation == "QueryTitle") { 
			queryMeetingByTitle(); return true;}
		if (t_operation == "QueryTime") { 
			queryMeetingByTimeInterval(); return true;}
		if (t_operation == "DeleteMeetingTitle") { 
			deleteMeetingByTitle(); return true;}
		if (t_operation == "DeleteMeeting") { 
			deleteAllMeetings(); return true;}
		if (t_operation == "Help") { 
			LogInSuccess(); return true; }
		if (t_operation == "Contiune") { 
			std::cout << "\nPlease Try Again\n"; Root(); }
	}
	return true;
}
void AgendaUI::quitAgenda() {
	std::cout << "\nThanks for your using!\n";
	Root();
}
void AgendaUI::userLogIn() {
	std::cout << "\n[log in] [user name] [password]" << std::endl;
	std::cout << "[log in] ";
	std::string userName, password;
	std::cin >> userName; std::cin >> password;
	if (m_agendaService.userLogIn(userName, password)) {
		m_userName = userName; m_userPassword = password;
		std::cout << "[log in] succeed!" << std::endl;
		LogInSuccess();
		std::string choice = "contiune";
		do {
			std::cout << "Agenda@" << m_userName << " # ";
			choice = getOperation();
		} while (executeOperation("LogIn", choice));
	} else {
		std::cout << "[error] log in fail!\n";
	}
	Root();
}

void AgendaUI::userRegister() {
	std::cout << "\n[register] [user name] [password] [email] [phone]" << std::endl;
	std::cout << "[register] ";
	std::string userName, password, email, phone;
	std::cin >> userName;
	std::cin >> password;
	std::cin >> email;
	std::cin >> phone;
	if (m_agendaService.userRegister(userName, password, email, phone)) {
		std::cout << "[register] succeed!" << std::endl;
	} else {
		std::cout << "[error] register fail!" << std::endl;
	}
	Root();
}
// void AgendaUI::userFindPassword() {
// 	std::cout << "\n[Find Password] [user name] [email] [phone]\n";
// 	std::cout << "[Find Password] ";
// 	std::string userName, email, phone;
// 	std::cin >> userName;
// 	std::cin >> email;
// 	std::cin >> phone;
// 	if (m_agendaService.userFindPassword(userName, email, phone)) {
// 		std::cout << "[Find Password] Succeed!\n";
// 		std::cout << "[Find Password] Please Input your new Password!\n";
// 		std::string password; std::cin >> password;
// 		m_agendaService.userchangePassword(userName, password);
// 		std::cout << "[Change Password] Succeed!\n";
// 	} else {
// 		std::cout << "[error] Find Password fail!\n";
// 	}
// }
void AgendaUI::userLogOut() {
	m_userName = "";
	m_userPassword = "";
	std::cout << "\n[User Log Out] Succeed! Thanks For Your Using!" << std::endl;
}
void AgendaUI::deleteUser() {
	if (m_agendaService.deleteUser(m_userName, m_userPassword)) {
		std::cout << "[delete Agenda account] succeed!" << std::endl;
	} else {
		std::cout << "[error] delete User fail!" << std::endl;
	}
}
void AgendaUI::listAllUsers() {
	std::cout << "\n[list all users]\n";
	std::list<User> UserList = m_agendaService.listAllUsers();
	int NameLength = 0, MailLength = 0, PhoneLength = 0;
	UserList.sort([](User a, User b)->bool {
		return a.getName() > b.getName();
	});
	for_each (UserList.begin(), UserList.end(),
				[&NameLength, &MailLength, &PhoneLength](User each) {

		if (each.getName().length() > NameLength) {
			NameLength = each.getName().length(); }
		if (each.getEmail().length() > MailLength) {
			MailLength = each.getEmail().length(); }
		if (each.getPhone().length() > PhoneLength) {
			PhoneLength = each.getPhone().length(); }
	});
	NameLength++; MailLength++; PhoneLength++;
	std::cout << std::left;
	std::cout << std::setw(NameLength) << "Name"
	<< std::setw(MailLength) << "Email"
	<< std::setw(PhoneLength)
	<< "Phone" << std::endl;
	for_each(UserList.begin(), UserList.end(),
			[&NameLength, &MailLength, &PhoneLength](User each) {
		std::cout << std::left;
		std::cout << std::setw(NameLength)
		<< each.getName() << std::setw(MailLength)
		<< each.getEmail() << std::setw(PhoneLength)
		<< each.getPhone() << std::endl;
	});
	Root();
}
void AgendaUI::createMeeting() {
	std::cout << "\n[create meeting] [the number of participators]" << std::endl;
	std::cout << "[create meeting] "; int number; std::cin >> number;
	std::vector<std::string> participators;
	std::string name;
	for (int i = 0; i < number; ++i) {
		std::cout << "create meeting] [please enter the participator " << i + 1 << "]" << std::endl; 
		std::cout << "[create meeting] "; std::cin >> name;
		participators.push_back(name);
	}
	std::cout << "[create meeting] [title][start time(yyyy-mm-dd/hh:mm)] [end time(yyyy-mm-dd)]" << std::endl;
	std::cout << "[create meeting] ";
	std::string title, start, end;
	std::cin >> title; std::cin >> start; std::cin >> end;
	if (m_agendaService.createMeeting(m_userName, title, start, end, participators)) {
		std::cout << "[create meeting] succeed!" << std::endl;
	} else {
		std::cout << "[create meeting] error!" << std::endl;
	}
	Root();
}
void AgendaUI::listAllMeetings() {
	std::cout << "\n[list all meetings]";
	std::list<Meeting> MeetingList =
		m_agendaService.listAllMeetings(m_userName);
	printMeetings(MeetingList);
}
void AgendaUI::listAllSponsorMeetings() {
	std::cout << "\n[list all sponsor meetings]";
	std::list<Meeting> MeetingList =
		m_agendaService.listAllSponsorMeetings(m_userName);
	printMeetings(MeetingList);
}

void AgendaUI::listAllParticipateMeetings() {
	std::cout << "\n[list all sponsor meetings]";
	std::list<Meeting> MeetingList =
		m_agendaService.listAllParticipateMeetings(m_userName);
	printMeetings(MeetingList);
}

void AgendaUI::queryMeetingByTitle() {
	std::cout << "\n[query meeting] [title]:\n";
	std::cout << "[query meeting] ";
	std::string t_title; std::cin >> t_title;
	std::list<Meeting> MeetingList =
		m_agendaService.meetingQuery(m_userName, t_title);
	printMeetings(MeetingList);
}
void AgendaUI::queryMeetingByTimeInterval() {
	std::cout << "\n[start time(yyyy-mm-dd/hh:mm)] [end time(yyyy-mm-dd/hh:mm)]\n";
	std::cout << "[query meetings] "; std::string t_start, t_end;
	std::cin >> t_start;
	std::cin >> t_end;
	std::list<Meeting> MeetingList =
		m_agendaService.meetingQuery(m_userName, t_start, t_end);
	std::cout << "[query meetings]\n\n";
	printMeetings(MeetingList);
}
void AgendaUI::deleteAllMeetings() {
	std::cout << "\n[delete all meeting]\n";
	if (m_agendaService.deleteAllMeetings(m_userName)) {
		std::cout << "\n[delete meeting by title] succeed!" << std::endl;
	} else {
		std::cout << "\n[error] delete meeting fail!\n";
	}
	Root();
}
void AgendaUI::deleteMeetingByTitle(void) {
		std::cout << "\n[delete meeting] [title]\n";
	std::cout << "[delete meeting] "; std::string title;
	std::cin >> title;
	if (m_agendaService.deleteMeeting(m_userName, title)) {
		std::cout << "\n[delete meeting by title] succeed!" << std::endl;
	} else {
		std::cout << "\n[error] delete meeting fail!\n";
	}
	Root();
}


void  AgendaUI::printMeetings(std::list<Meeting> MeetingList) {
	if (MeetingList.empty()) {
		std::cout << "\nNope Meeting!\n";
		Root();
		return;
	}
	std::cout << "\n";
	int SponsorLength = 0, TitleLength = 0;
	for_each(MeetingList.begin(), MeetingList.end(),
				[&SponsorLength, &TitleLength](Meeting each) {
		if (each.getSponsor().length() > SponsorLength) {
			SponsorLength = each.getSponsor().length(); }
		if (each.getTitle().length() > TitleLength) { 
			TitleLength = each.getTitle().length(); }
	});
	SponsorLength++; TitleLength++;
	SponsorLength >= 8 ?  : SponsorLength = 8;
	std::cout << std::left;
	std::cout << setw(TitleLength) << "Title"
	<< setw(SponsorLength) << "Sponsor" << setw(17)
	<< "Start Time" << setw(17) << "End Time"
	<< "Participatos\n";
	MeetingList.sort([](Meeting a, Meeting b) -> bool {
		return a.getStartDate() < b.getStartDate();
	});
	for_each (MeetingList.begin(), MeetingList.end(),
		[&TitleLength, &SponsorLength](Meeting& each) {
		std::cout << std::left;
		std::cout << setw(TitleLength) << each.getTitle()
		<< setw(SponsorLength) << each.getSponsor()
		<< setw(17) << Date::dateToString(each.getStartDate())
		<< setw(17) << Date::dateToString(each.getEndDate());
		bool first = true;
		for (std::string str : each.getParticipator()) {
			if (first && str != "") {
				std::cout << str;
				first = false;
			} else if (!first && str != "") {
				std::cout << "," << str;
				first = false;
			}
		}
		std::cout << std::endl;
	});
	Root();
}
