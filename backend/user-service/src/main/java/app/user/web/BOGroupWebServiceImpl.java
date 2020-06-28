package app.user.web;

import app.user.api.BOGroupWebService;
import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.service.BOGroupService;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
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
    public EmptyResponse create(BOCreateGroupRequest request) {
        Response<String> response = ResponseHelper.encloseWithException(() -> boGroupService.create(request));
        logger.info("created group, id = {}", response.data);
        return response;
    }

    @Override
    public Response<BOListGroupResponse> list() {
        return ResponseHelper.okOf(boGroupService.list());
    }

    @Override
    public Response<BOGetGroupResponse> get(String id) {
        return ResponseHelper.okOf(boGroupService.get(id));
    }

    @Override
    public EmptyResponse remove(String id) {
        logger.info("removed group, id = {}", id);
        Response<Object> response = ResponseHelper.encloseWithException(() -> boGroupService.remove(id));
        boGroupService.remove(id);
        return response;
    }

    @Override
    public EmptyResponse createRole(String groupId, BOCreateRoleRequest request) {
        Response<String> response = ResponseHelper.encloseWithException(() -> boGroupService.createRole(groupId, request));
        logger.info("created role, id = {}", response.data);
        return response;
    }

    @Override
    public EmptyResponse removeRole(String groupId, String id) {
        logger.info("removed role, id = {}", id);
        return ResponseHelper.encloseWithException(() -> boGroupService.removeRole(groupId, id));
    }
}
