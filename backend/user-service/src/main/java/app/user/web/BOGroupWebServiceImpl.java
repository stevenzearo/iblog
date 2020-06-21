package app.user.web;

import app.user.api.BOGroupWebService;
import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.group.BORemoveGroupRequest;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.api.admin.role.BORemoveRoleRequest;
import app.user.service.BOGroupService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author steve
 */
public class BOGroupWebServiceImpl implements BOGroupWebService {
    @Autowired
    BOGroupService boGroupService;

    @Override
    public void create(BOCreateGroupRequest request) {

    }

    @Override
    public BOListGroupResponse list() {
        return null;
    }

    @Override
    public BOGetGroupResponse get(String id) {
        return null;
    }

    @Override
    public void remove(String id, BORemoveGroupRequest request) {

    }

    @Override
    public void createRole(String groupId, BOCreateRoleRequest request) {

    }

    @Override
    public void removeRole(String groupId, String id, BORemoveRoleRequest request) {

    }
}
