package app.site.api;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import app.site.service.ChatMessageService;
import app.site.web.interceptor.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
@LoginRequired
public class ChatGroupAJAXWebServiceImpl implements ChatGroupAJAXWebService {
    private final Logger logger = LoggerFactory.getLogger(ChatGroupAJAXWebServiceImpl.class);
    @Autowired
    ChatMessageService chatMessageService;

    @Override
    public CreateAndJoinGroupAJAXResponse createAndJoin() {
        return null;
    }

    @Override
    public ListChatGroupAJAXResponse list() {
        return null;
    }

    @Override
    public void join(String groupId) {

    }

    @Override
    public SearchChatMessageAJAXResponse search(String groupId) {
        logger.debug(String.format("search chat message by group, groupId = %s", groupId));
        return chatMessageService.search(groupId);
    }

    @Override
    public void leave(String groupId) {

    }
}
