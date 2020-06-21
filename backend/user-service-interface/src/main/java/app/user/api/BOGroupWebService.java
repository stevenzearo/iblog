package app.user.api;

import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.group.BORemoveGroupRequest;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.api.admin.role.BORemoveRoleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("user-web-service")
public interface BOGroupWebService {
    @RequestMapping(value = "/bo/group", method = RequestMethod.POST)
    void create(@RequestBody BOCreateGroupRequest request);

    @RequestMapping(value = "/bo/group", method = RequestMethod.GET)
    BOListGroupResponse list();

    @RequestMapping(value = "/bo/group/{groupId}", method = RequestMethod.GET)
    BOGetGroupResponse get(@PathVariable("groupId") String id);

    @RequestMapping(value = "/bo/group/{groupId}", method = RequestMethod.DELETE)
    void remove(@PathVariable("groupId") String id, @RequestBody BORemoveGroupRequest request);

    @RequestMapping(value = "/bo/group/{groupId}/role", method = RequestMethod.POST)
    void createRole(@PathVariable("groupId") String groupId, @RequestBody BOCreateRoleRequest request);

    @RequestMapping(value = "/bo/group/{groupId}/role/{id}", method = RequestMethod.DELETE)
    void removeRole(@PathVariable("groupId") String groupId, @PathVariable("id") String id, @RequestBody BORemoveRoleRequest request);
}
