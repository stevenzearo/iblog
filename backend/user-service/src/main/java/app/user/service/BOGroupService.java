package app.user.service;

import app.user.api.admin.group.BOCreateGroupRequest;
import app.user.api.admin.group.BOGetGroupResponse;
import app.user.api.admin.group.BOListGroupResponse;
import app.user.AuthorityView;
import app.user.api.admin.role.BOCreateRoleRequest;
import app.user.dao.GroupRepository;
import app.user.dao.RoleRepository;
import app.user.domain.Authority;
import app.user.domain.Group;
import app.user.domain.Role;
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
    @Autowired
    RoleRepository roleRepository;

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
        Group group = groupRepository.getById(id);
        List<Role> roles = roleRepository.findByGroupId(group.id);
        BOGetGroupResponse response = new BOGetGroupResponse();
        response.id = group.id;
        response.name = group.name;
        response.roles = roles.stream().map(role -> {
            BOGetGroupResponse.Role roleView = new BOGetGroupResponse.Role();
            roleView.name = role.name;
            roleView.authority = AuthorityView.valueOf(role.authority.name());
            return roleView;
        }).collect(Collectors.toList());
        return response;
    }

    public void remove(String id) {
        groupRepository.removeById(id);
    }

    public String createRole(String groupId, BOCreateRoleRequest request) {
        Role role = new Role();
        role.id = UUID.randomUUID().toString();
        role.groupId = groupId;
        role.name = request.name;
        role.authority = Authority.valueOf(request.authority.name());
        role.createBy = request.requestedBy;
        role.createdTime = ZonedDateTime.now();
        roleRepository.save(role);
        return role.id;
    }

    public void removeRole(String groupId, String id) throws Exception {
        Group group = groupRepository.getById(groupId);
        if (group == null) throw new Exception(String.format("group not found, id = %s", groupId));
        roleRepository.removeById(id);
    }
}
