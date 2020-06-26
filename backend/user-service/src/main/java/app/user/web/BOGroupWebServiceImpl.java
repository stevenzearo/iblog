package app.user.web;

import app.user.api.BOGroupWebService;
import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.service.BOGroupService;
import app.web.error.NotFoundException;
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
    public void create(BOCreateGroupRequest request) {
        String id = boGroupService.create(request);
        logger.info("created group, id = {}", id);
    }

    @Override
    public BOListGroupResponse list() {
        return boGroupService.list();
    }

    @Override
    public BOGetGroupResponse get(String id) {
        return boGroupService.get(id);
    }

    @Override
    public void remove(String id) {
        logger.info("removed group, id = {}", id);
        boGroupService.remove(id);
    }

    @Override
    public void createRole(String groupId, BOCreateRoleRequest request) throws NotFoundException {
        String roleId = boGroupService.createRole(groupId, request);
        logger.info("created role, id = {}", roleId);
    }

    @Override
    public void removeRole(String groupId, String id) throws NotFoundException {
        logger.info("removed role, id = {}", id);
        boGroupService.removeRole(groupId, id);
    }
}
