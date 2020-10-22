package app.site.service;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Service
public class ChatMessageService {
    public CreateAndJoinGroupAJAXResponse createAndJoin() {
        CreateAndJoinGroupAJAXResponse response = new CreateAndJoinGroupAJAXResponse();
        response.groupId = "group-0001";
        response.createdTime = ZonedDateTime.now();
        return response;
    }

    public ListChatGroupAJAXResponse list() {
        return new ListChatGroupAJAXResponse();
    }

    public void join(String groupId) {

    }

    public SearchChatMessageAJAXResponse search(String groupId) {
        return new SearchChatMessageAJAXResponse();
    }

    public void leave(String groupId) {

    }
}
