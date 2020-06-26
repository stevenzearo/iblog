package app.site.service;

import app.site.api.admin.CreateGroupAJAXRequest;
import app.site.api.admin.CreateRoleAJAXRequest;
import app.site.api.admin.GetGroupAJAXResponse;
import app.site.api.admin.ListGroupAJAXResponse;
import app.user.api.BOGroupWebService;
import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class GroupService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    @Qualifier("group-web-service")
    @Autowired(required = false)
    BOGroupWebService boGroupWebService;

    public void create(CreateGroupAJAXRequest request) {
        BOCreateGroupRequest boRequest = new BOCreateGroupRequest();
        boRequest.name = request.name;
        boRequest.requestedBy = REQUESTED_BY;
        boGroupWebService.create(boRequest);
    }

    public ListGroupAJAXResponse list() {
        BOListGroupResponse boResponse = boGroupWebService.list();
        List<ListGroupAJAXResponse.Group> groups = boResponse.groups.stream().map(this::buildListGroupView).collect(Collectors.toList());
        ListGroupAJAXResponse response = new ListGroupAJAXResponse();
        response.groups = groups;
        return response;
    }

    public GetGroupAJAXResponse get(String id) {
        BOGetGroupResponse boResponse = boGroupWebService.get(id);
        GetGroupAJAXResponse response = new GetGroupAJAXResponse();
        response.id = boResponse.id;
        response.name = boResponse.name;
        response.roles = boResponse.roles.stream().map(this::buildGetGroupRoleView).collect(Collectors.toList());
        return response;
    }

    public void remove(String id) {
        boGroupWebService.remove(id);
    }

    public void createRole(String groupId, CreateRoleAJAXRequest request) throws NotFoundException {
        BOCreateRoleRequest boRequest = new BOCreateRoleRequest();
        boRequest.name = request.name;
        boRequest.authority = request.authority;
        boRequest.requestedBy = REQUESTED_BY;
        boGroupWebService.createRole(groupId, boRequest);
    }

    public void removeRole(String groupId, String id) throws NotFoundException {
        boGroupWebService.removeRole(groupId, id);
    }

    private ListGroupAJAXResponse.Group buildListGroupView(BOListGroupResponse.Group group) {
        ListGroupAJAXResponse.Group groupView = new ListGroupAJAXResponse.Group();
        groupView.id = group.id;
        groupView.name = group.name;
        return groupView;
    }

    private GetGroupAJAXResponse.Role buildGetGroupRoleView(BOGetGroupResponse.Role role) {
        GetGroupAJAXResponse.Role roleView = new GetGroupAJAXResponse.Role();
        roleView.name = role.name;
        roleView.authority = role.authority;
        return roleView;
    }
}
