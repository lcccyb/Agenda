package src;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yanzexin on 16/8/31.
 * All right reserved.
 */
public class AgendaServiceTest {
    AgendaService m_service = new AgendaService();

    @Test
    public void queryMeetingByTitle() throws Exception {
        for (Meeting t_meeting : m_service.queryMeetingByTitle("Summer", "Test")) {
            System.out.println(t_meeting.getTitle());
        }
    }

    @Test
    public void listAllMeetings() throws Exception {
        System.out.println(m_service.listAllMeetings("Summer"));
        for (Meeting t_meeting : m_service.listAllMeetings("Summer")) {
            System.out.print(t_meeting.getSponsor());
        }
    }

    @Test
    public void listSponsorMeetings() throws Exception {
        for (Meeting t_meeting : m_service.listAllMeetings("Summer")) {
            System.out.print(t_meeting.getSponsor());
        }
    }

    @Test
    public void listAllParticipatorMeetings() throws Exception {

    }

    @org.junit.Test
    public void userRegister() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(true, m_service.userRegister("stary", "12345", "yan", "ze"));
        Assert.assertEquals(true, m_service.userRegister("yan", "123", "test", "test"));
    }

    @org.junit.Test
    public void userLogIn() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(false, m_service.userLogIn("yan", "1234"));
        Assert.assertEquals(true, m_service.userLogIn("stary", "12345"));
    }

    @org.junit.Test
    public void deleteUser() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(false, m_service.deleteUser("stary", "123"));
        Assert.assertEquals(true, m_service.deleteUser("stary", "12345"));
    }

    @org.junit.Test
    public void changePassword() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(false, m_service.changePassword("stary", "123", "1234"));
        Assert.assertEquals(true, m_service.changePassword("yan", "123", "1234"));
    }

    @org.junit.Test
    public void changeEmail() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(false, m_service.changeEmail("yan", "123", "ze"));
        Assert.assertEquals(true, m_service.changeEmail("yan", "1234", "ze"));
    }

    @org.junit.Test
    public void changePhone() throws Exception {
        AgendaService m_service = new AgendaService();
        Assert.assertEquals(false, m_service.changePhone("yan", "123", "xin"));
        Assert.assertEquals(true, m_service.changePhone("yan", "1234", "ze"));
    }

}