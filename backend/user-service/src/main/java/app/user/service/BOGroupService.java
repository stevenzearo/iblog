package app.user.service;

import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.group.BORemoveGroupRequest;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.api.admin.role.BORemoveRoleRequest;

/**
 * @author steve
 */
public class BOGroupService {
    public void create(BOCreateGroupRequest request) {

    }

    public BOListGroupResponse list() {
        return null;
    }

    public BOGetGroupResponse get(String id) {
        return null;
    }

    public void remove(String id, BORemoveGroupRequest request) {

    }

    public void createRole(String groupId, BOCreateRoleRequest request) {

    }

    public void removeRole(String groupId, String id, BORemoveRoleRequest request) {

    }
}
