package src;

import org.junit.Test;

/**
 * Created by yanzexin on 16/9/2.
 * All right reserved.
 */
public class StorageTest {
    @Test
    public void queryMeeting() throws Exception {
        Storage m_storage = Storage.getInstance();
        System.out.print(m_storage.queryMeeting(new MeetingFilter() {
            @Override
            public boolean filter(Meeting t_meeting) {
                return (t_meeting.getSponsor().equals("Summer") || t_meeting.isParticipator("Summer"));
            }
        }));
    }

    @Test
    public void getInstance() throws Exception {
        Storage m_storage = Storage.getInstance();
        for (Meeting t_meeting :
                m_storage.m_meetingList) {
            System.out.println(t_meeting.getSponsor());
        }
    }

}