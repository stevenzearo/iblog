package app.site.api;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXRequest;
import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.SearchChatMessageAJAXResponse;
import app.web.error.ConflictException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    CreateAndJoinGroupAJAXResponse createAndJoin(String auth, @RequestBody CreateAndJoinGroupAJAXRequest request) throws ConflictException;

    @RequestMapping(value = "/ajax/group", method = RequestMethod.GET)
    ListChatGroupAJAXResponse list(String auth);

    @RequestMapping(value = "/ajax/group/{groupId}/join", method = RequestMethod.PUT)
    void join(String auth, @PathParam("groupId") String groupId);

    @RequestMapping(value = "/ajax/group/{groupId}/message", method = RequestMethod.PUT)
    SearchChatMessageAJAXResponse search(String auth, @PathParam("groupId") String groupId);

    @RequestMapping(value = "/ajax/group/{groupId}/leave", method = RequestMethod.PUT)
    void leave(String auth, @PathParam("groupId") String groupId);
}
