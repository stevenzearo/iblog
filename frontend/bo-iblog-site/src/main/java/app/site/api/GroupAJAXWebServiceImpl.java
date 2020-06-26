package app.site.api;

import app.site.api.admin.CreateGroupAJAXRequest;
import app.site.api.admin.CreateRoleAJAXRequest;
import app.site.api.admin.GetGroupAJAXResponse;
import app.site.api.admin.ListGroupAJAXResponse;
import app.site.service.GroupService;
import app.web.error.NotFoundException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class GroupAJAXWebServiceImpl implements GroupAJAXWebService {
    @Autowired
    GroupService groupService;

    @Override
    public void create(CreateGroupAJAXRequest request) {
        groupService.create(request);
    }

    @Override
    public ListGroupAJAXResponse list() {
        return groupService.list();
    }

    @Override
    public GetGroupAJAXResponse get(String id) {
        return groupService.get(id);
    }

    @Override
    public void remove(String id) {
        groupService.remove(id);
    }

    @Override
    public void createRole(String groupId, CreateRoleAJAXRequest request) throws NotFoundException {
        groupService.createRole(groupId, request);
    }

    @Override
    public void removeRole(String groupId, String id) throws WebException {
        groupService.removeRole(groupId, id);
    }
}
