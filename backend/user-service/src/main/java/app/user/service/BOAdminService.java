package app.user.service;

import app.view.user.AuthorityView;
import app.user.ErrorCodes;
import app.util.PasswordEncryptException;
import app.util.PasswordEncryptHelper;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.BOGetAdminByIdResponse;
import app.user.dao.AdminRepository;
import app.user.dao.GroupRepository;
import app.user.dao.RoleRepository;
import app.user.domain.Admin;
import app.user.domain.Group;
import app.user.domain.Role;
import app.web.error.ConflictException;
import app.web.error.NotFoundException;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class BOAdminService {
    private static final int MAX_ITERATED_TIME = 10;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RoleRepository roleRepository;

    public String create(BOCreateAdminRequest request) throws WebException {
        Admin existAdmin = adminRepository.getFirstByEmail(request.email);
        if (existAdmin != null)
            throw new ConflictException(ErrorCodes.EMAIL_EXIST, String.format("email already exist, email = %s", request.email));
        Group group = groupRepository.getById(request.groupId);
        if (group == null)
            throw new NotFoundException(ErrorCodes.GROUP_NOT_FOUND, String.format("group not found, groupId = %s", request.groupId));

        int byteVal = (int) Math.round(Math.random() * (int) Byte.MAX_VALUE) + 1; // 1 ~ 127
        char[] saltChar = Character.toChars(byteVal);
        String salt = new String(saltChar);
        int iteratedTimes = (int) (Math.random() * MAX_ITERATED_TIME) + 1;

        Admin admin = buildAdmin(request, salt, iteratedTimes);
        adminRepository.save(admin);
        return admin.id;
    }

    public BOGetAdminByIdResponse getById(String id) {
        Admin admin = adminRepository.getById(id);
        Group group = groupRepository.getById(admin.groupId);
        List<Role> roles = roleRepository.findByGroupId(admin.groupId);
        return buildAdminForGetById(admin, group, roles);
    }

    public BOGetAdminByEmailResponse getByEmail(String email) {
        Admin admin = adminRepository.getFirstByEmail(email);
        Group group = groupRepository.getById(admin.groupId);
        List<Role> roles = roleRepository.findByGroupId(admin.groupId);
        return buildBoGetAdminResponse(admin, group, roles);
    }

    private Admin buildAdmin(BOCreateAdminRequest request, String salt, int iteratedTimes) throws PasswordEncryptException {
        Admin admin = new Admin();
        admin.id = UUID.randomUUID().toString();
        admin.groupId = request.groupId;
        admin.name = request.name;
        admin.email = request.email;
        admin.salt = salt;
        admin.iteratedTimes = iteratedTimes;
        admin.password = PasswordEncryptHelper.encryptPassword(request.password, admin.salt, iteratedTimes);
        admin.createBy = request.requestedBy;
        admin.createdTime = ZonedDateTime.now();
        return admin;
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
        groupView.roles = roles.stream().map(this::buildRoleView).collect(Collectors.toList());
        return groupView;
    }

    private BOGetAdminByEmailResponse.Role buildRoleView(Role role) {
        BOGetAdminByEmailResponse.Role roleView = new BOGetAdminByEmailResponse.Role();
        roleView.name = role.name;
        roleView.authority = AuthorityView.valueOf(role.authority.name());
        return roleView;
    }

    private BOGetAdminByIdResponse buildAdminForGetById(Admin admin, Group group, List<Role> roles) {
        BOGetAdminByIdResponse response = new BOGetAdminByIdResponse();
        response.id = admin.id;
        response.name = admin.name;
        response.email = admin.email;
        response.group = buildGroupForGetById(group, roles);
        return response;
    }

    private BOGetAdminByIdResponse.Group buildGroupForGetById(Group group, List<Role> roles) {
        BOGetAdminByIdResponse.Group groupView = new BOGetAdminByIdResponse.Group();
        groupView.id = group.id;
        groupView.name = group.name;
        groupView.roles = roles.stream().map(this::buildRoleForGetById).collect(Collectors.toList());
        return groupView;
    }

    private BOGetAdminByIdResponse.Role buildRoleForGetById(Role role) {
        BOGetAdminByIdResponse.Role roleView = new BOGetAdminByIdResponse.Role();
        roleView.name = role.name;
        roleView.authority = AuthorityView.valueOf(role.authority.name());
        return roleView;
    }
}
