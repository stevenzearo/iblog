package app.user.web;

import app.user.api.BOGroupWebService;
import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.service.BOGroupService;
import app.web.error.NotFoundException;
import app.web.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class BOGroupWebServiceImpl implements BOGroupWebService {
    private final Logger logger = LoggerFactory.getLogger(BOGroupWebServiceImpl.class);
    @Autowired
    BOGroupService boGroupService;

    @Override
    public Response<Object> create(BOCreateGroupRequest request) {
        String id = boGroupService.create(request);
        logger.info("created group, id = {}", id);
        return Response.ok();
    }

    @Override
    public Response<BOListGroupResponse> list() {
        return Response.okOf(boGroupService.list());
    }

    @Override
    public Response<BOGetGroupResponse> get(String id) {
        return Response.okOf(boGroupService.get(id));
    }

    @Override
    public Response<Object> remove(String id) {
        logger.info("removed group, id = {}", id);
        boGroupService.remove(id);
        return Response.ok();
    }

    @Override
    public Response<Object> createRole(String groupId, BOCreateRoleRequest request) {
        Response.encloseWithException(() -> boGroupService.createRole(groupId, request));
        String roleId = boGroupService.createRole(groupId, request);
        logger.info("created role, id = {}", roleId);
        return Response.ok();
    }

    @Override
    public Response<Object> removeRole(String groupId, String id) {
        logger.info("removed role, id = {}", id);
        boGroupService.removeRole(groupId, id);
        return Response.ok();
    }
}
