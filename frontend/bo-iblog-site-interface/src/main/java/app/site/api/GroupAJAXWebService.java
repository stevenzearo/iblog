package app.site.api;

import app.site.api.admin.CreateGroupAJAXRequest;
import app.site.api.admin.CreateRoleAJAXRequest;
import app.site.api.admin.GetGroupAJAXResponse;
import app.site.api.admin.ListGroupAJAXResponse;
import app.web.error.NotFoundException;
import app.web.error.WebException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface GroupAJAXWebService {

    @RequestMapping(value = "/ajax/group", method = RequestMethod.POST)
    void create(@RequestBody CreateGroupAJAXRequest request);

    @RequestMapping(value = "/ajax/group", method = RequestMethod.GET)
    ListGroupAJAXResponse list();

    @RequestMapping(value = "/ajax/group/{groupId}", method = RequestMethod.GET)
    GetGroupAJAXResponse get(@PathVariable("groupId") String id);

    @RequestMapping(value = "/ajax/group/{groupId}", method = RequestMethod.DELETE)
    void remove(@PathVariable("groupId") String id);

    @RequestMapping(value = "/ajax/group/{groupId}/role", method = RequestMethod.POST)
    void createRole(@PathVariable("groupId") String groupId, @RequestBody CreateRoleAJAXRequest request) throws NotFoundException;

    @RequestMapping(value = "/ajax/group/{groupId}/role/{id}", method = RequestMethod.DELETE)
    void removeRole(@PathVariable("groupId") String groupId, @PathVariable("id") String id) throws WebException;
}
