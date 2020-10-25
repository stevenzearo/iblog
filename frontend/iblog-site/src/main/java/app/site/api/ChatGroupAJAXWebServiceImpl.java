package app.site.api;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXRequest;
import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.GetCroupChatMessageAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.cache.User;
import app.site.service.ChatGroupService;
import app.site.service.UserService;
import app.site.web.Context;
import app.site.web.interceptor.LoginRequired;
import app.web.error.ConflictException;
import app.web.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author steve
 */
@Component
@LoginRequired
public class ChatGroupAJAXWebServiceImpl implements ChatGroupAJAXWebService {
    private final Logger logger = LoggerFactory.getLogger(ChatGroupAJAXWebServiceImpl.class);
    @Autowired
    UserService userService;
    @Autowired
    ChatGroupService chatGroupService;

    @Override
    public CreateAndJoinGroupAJAXResponse createAndJoin(@RequestHeader(Context.AUTH_ID) String auth, CreateAndJoinGroupAJAXRequest request) throws ConflictException {
        User currentUser = userService.getCurrentUser(auth);
        CreateAndJoinGroupAJAXResponse response = chatGroupService.createAndJoin(request, currentUser.id);
        logger.debug(String.format("created and joined group, groupId = %s", response.groupId));
        return response;
    }

    @Override
    public ListChatGroupAJAXResponse list() {
        return chatGroupService.list();
    }

    @Override
    public void join(@RequestHeader(Context.AUTH_ID) String auth, String groupId) throws NotFoundException {
        User currentUser = userService.getCurrentUser(auth);
        chatGroupService.join(groupId, currentUser.id);
        logger.debug(String.format("user join in group, groupId = %s, userId = %s", groupId, currentUser.id));
    }

    @Override
    public GetCroupChatMessageAJAXResponse getChatMessages(@RequestHeader(Context.AUTH_ID) String auth, String groupId) throws NotFoundException {
        logger.debug(String.format("get group chat messages, groupId = %s", groupId));
        return chatGroupService.getChatMessages(groupId);
    }

    @Override
    public void leave(@RequestHeader(Context.AUTH_ID) String auth, String groupId) throws NotFoundException {
        User currentUser = userService.getCurrentUser(auth);
        chatGroupService.leave(groupId, currentUser.id);
        logger.debug(String.format("user leave in group, groupId = %s, userId = %s", groupId, currentUser.id));
    }
}
