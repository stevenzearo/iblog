package app.site.service;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXRequest;
import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import app.site.cache.ChatGroup;
import app.site.cache.ChatGroupCache;
import app.site.cache.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author steve
 */
@Service
public class ChatGroupService {
    @Autowired
    ChatGroupCache chatGroupCache;

    public CreateAndJoinGroupAJAXResponse createAndJoin(CreateAndJoinGroupAJAXRequest request, User user) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.id = UUID.randomUUID().toString();
        chatGroup.name = request.name;
        chatGroup.createdUserId = user.id;
        chatGroup.createdTime = ZonedDateTime.now();
        chatGroupCache.save(chatGroup);


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
