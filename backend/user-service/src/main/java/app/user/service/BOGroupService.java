package app.user.service;

import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.api.admin.group.BORemoveGroupRequest;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.api.admin.role.BORemoveRoleRequest;
import app.user.dao.GroupRepository;
import app.user.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author steve
 */
public class BOGroupService {
    @Autowired
    GroupRepository groupRepository;

    public String create(BOCreateGroupRequest request) {
        Group group = new Group();
        group.id = UUID.randomUUID().toString();
        group.name = request.name;
        group.createBy = request.requestedBy;
        group.createdTime = ZonedDateTime.now();
        groupRepository.save(group);
        return group.id;
    }

    public BOListGroupResponse list() {
        List<Group> groups = groupRepository.find();
        BOListGroupResponse response = new BOListGroupResponse();
        response.groups = groups.stream().map(group -> {
            BOListGroupResponse.Group groupView = new BOListGroupResponse.Group();
            groupView.id = group.id;
            groupView.name = group.name;
            return groupView;
        }).collect(Collectors.toList());
        return response;
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
