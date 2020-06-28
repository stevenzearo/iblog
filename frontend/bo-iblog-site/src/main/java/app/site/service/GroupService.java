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
import app.web.error.WebException;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class GroupService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    @Autowired
    BOGroupWebService boGroupWebService;

    public void create(CreateGroupAJAXRequest request) throws WebException {
        BOCreateGroupRequest boRequest = new BOCreateGroupRequest();
        boRequest.name = request.name;
        boRequest.requestedBy = REQUESTED_BY;
        EmptyResponse response = boGroupWebService.create(boRequest);
        ResponseHelper.checkStatusCode(response);
    }

    public ListGroupAJAXResponse list() throws WebException {
        Response<BOListGroupResponse> boResponse = boGroupWebService.list();
        BOListGroupResponse data = ResponseHelper.fetchDataWithException(boResponse);
        List<ListGroupAJAXResponse.Group> groups = data.groups.stream().map(this::buildListGroupView).collect(Collectors.toList());
        ListGroupAJAXResponse response = new ListGroupAJAXResponse();
        response.groups = groups;
        return response;
    }

    public GetGroupAJAXResponse get(String id) throws WebException {
        Response<BOGetGroupResponse> boResponse = boGroupWebService.get(id);
        BOGetGroupResponse data = ResponseHelper.fetchDataWithException(boResponse);
        GetGroupAJAXResponse response = new GetGroupAJAXResponse();
        response.id = data.id;
        response.name = data.name;
        response.roles = data.roles.stream().map(this::buildGetGroupRoleView).collect(Collectors.toList());
        return response;
    }

    public void remove(String id) throws WebException {
        EmptyResponse response = boGroupWebService.remove(id);
        ResponseHelper.checkStatusCode(response);
    }

    public void createRole(String groupId, CreateRoleAJAXRequest request) throws WebException {
        BOCreateRoleRequest boRequest = new BOCreateRoleRequest();
        boRequest.name = request.name;
        boRequest.authority = request.authority;
        boRequest.requestedBy = REQUESTED_BY;
        EmptyResponse response = boGroupWebService.createRole(groupId, boRequest);
        ResponseHelper.checkStatusCode(response);
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
