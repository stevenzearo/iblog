package app.user.api;

import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.web.response.EmptyResponse;
import app.web.response.Response;
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
@FeignClient(value = "user-web-service", qualifier = "group-web-service")
public interface BOGroupWebService {
    @RequestMapping(value = "/bo/group", method = RequestMethod.POST)
    EmptyResponse create(@RequestBody BOCreateGroupRequest request);

    @RequestMapping(value = "/bo/group", method = RequestMethod.GET)
    Response<BOListGroupResponse> list();

    @RequestMapping(value = "/bo/group/{groupId}", method = RequestMethod.GET)
    Response<BOGetGroupResponse> get(@PathVariable("groupId") String id);

    @RequestMapping(value = "/bo/group/{groupId}", method = RequestMethod.DELETE)
    EmptyResponse remove(@PathVariable("groupId") String id);

    @RequestMapping(value = "/bo/group/{groupId}/role", method = RequestMethod.POST)
    EmptyResponse createRole(@PathVariable("groupId") String groupId, @RequestBody BOCreateRoleRequest request);

    @RequestMapping(value = "/bo/group/{groupId}/role/{id}", method = RequestMethod.DELETE)
    EmptyResponse removeRole(@PathVariable("groupId") String groupId, @PathVariable("id") String id);
}
