package app.site.api;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXRequest;
import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import app.site.cache.User;
import app.site.service.ChatGroupService;
import app.site.service.UserService;
import app.site.web.Context;
import app.site.web.interceptor.LoginRequired;
import app.web.error.ConflictException;
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

        return null;
    }

    @Override
    public ListChatGroupAJAXResponse list(@RequestHeader(Context.AUTH_ID) String auth) {
        return null;
    }

    @Override
    public void join(@RequestHeader(Context.AUTH_ID) String auth, String groupId) {

    }

    @Override
    public SearchChatMessageAJAXResponse search(@RequestHeader(Context.AUTH_ID) String auth, String groupId) {
        logger.debug(String.format("search chat message by group, groupId = %s", groupId));
        return chatGroupService.search(groupId);
    }

    @Override
    public void leave(@RequestHeader(Context.AUTH_ID) String auth, String groupId) {

    }
}
