package app.site.api;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author steve
 */
@RestController
public interface ChatGroupAJAXWebService {
    @RequestMapping(value = "/ajax/group", method = RequestMethod.POST)
    CreateAndJoinGroupAJAXResponse createAndJoin();

    @RequestMapping(value = "/ajax/group", method = RequestMethod.GET)
    ListChatGroupAJAXResponse list();

    @RequestMapping(value = "/ajax/group/{groupId}/join", method = RequestMethod.PUT)
    void join(@PathParam("groupId") String groupId);

    @RequestMapping(value = "/ajax/group/{groupId}/message", method = RequestMethod.PUT)
    SearchChatMessageAJAXResponse search(@PathParam("groupId") String groupId);

    @RequestMapping(value = "/ajax/group/{groupId}/leave", method = RequestMethod.PUT)
    void leave(@PathParam("groupId") String groupId);
}
