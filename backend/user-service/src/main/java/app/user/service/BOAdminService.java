package app.user.service;

import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.role.AuthorityView;
import app.user.dao.AdminRepository;
import app.user.dao.GroupRepository;
import app.user.dao.RoleRepository;
import app.user.domain.Admin;
import app.user.domain.Group;
import app.user.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class BOAdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RoleRepository roleRepository;

    public void create(BOCreateAdminRequest request) throws Exception {
        Admin admin = new Admin();
        admin.id = UUID.randomUUID().toString();
        admin.name = request.name;
        Admin existAdmin = adminRepository.getFirstByEmail(request.email);
        if (existAdmin != null) throw new Exception(String.format("email already exist, %s", request.email));
        admin.email = request.email;
        // todo
    }

    public BOGetAdminByEmailResponse getByEmail(String email) {
        Admin admin = adminRepository.getFirstByEmail(email);
        Group group = groupRepository.getById(admin.groupId);
        List<Role> roles = roleRepository.findByGroupId(admin.groupId);
        return buildBoGetAdminResponse(admin, group, roles);
    }

    private BOGetAdminByEmailResponse buildBoGetAdminResponse(Admin admin, Group group, List<Role> roles) {
        BOGetAdminByEmailResponse adminResponse = new BOGetAdminByEmailResponse();
        adminResponse.id = admin.id;
        adminResponse.name = admin.name;
        adminResponse.email = admin.email;
        adminResponse.password = admin.password;
        adminResponse.salt = admin.salt;
        adminResponse.iteratedTimes = admin.iteratedTimes;
        adminResponse.group = buildGroupView(group, roles);
        return adminResponse;
    }

    private BOGetAdminByEmailResponse.Group buildGroupView(Group group, List<Role> roles) {
        BOGetAdminByEmailResponse.Group groupView = new BOGetAdminByEmailResponse.Group();
        groupView.id = group.id;
        groupView.name = group.name;
        groupView.roles = roles.stream().map(this::bulidRoleView).collect(Collectors.toList());
        return groupView;
    }

    private BOGetAdminByEmailResponse.Role bulidRoleView(Role role) {
        BOGetAdminByEmailResponse.Role roleView = new BOGetAdminByEmailResponse.Role();
        roleView.name = role.name;
        roleView.authority = AuthorityView.valueOf(role.authority.name());
        return roleView;
    }
}
