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
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.Arrays;
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

    public String create(BOCreateAdminRequest request) throws Exception {
        Admin existAdmin = adminRepository.getFirstByEmail(request.email);
        if (existAdmin != null) throw new Exception(String.format("email already exist, %s", request.email));

        int byteVal = (int) Math.round(Math.random() * (int) Byte.MAX_VALUE) + 1; // 1 ~ 127
        char[] saltChar = Character.toChars(byteVal);
        String salt = new String(saltChar);
        int iterated_times = (int) (Math.random() * MAX_ITERATED_TIME) + 1;

        Admin admin = buildAdmin(request, salt, iterated_times);
        adminRepository.save(admin);
        return admin.id;
    }

    private Admin buildAdmin(BOCreateAdminRequest request, String salt, int iterated_times) {
        Admin admin = new Admin();
        admin.id = UUID.randomUUID().toString();
        admin.groupId = request.groupId;
        admin.name = request.name;
        admin.email = request.email;
        admin.salt = salt;
        admin.iteratedTimes = iterated_times;
        admin.password = encryptPassword(request.password, admin.salt, iterated_times);
        admin.createBy = request.requestedBy;
        admin.createdTime = ZonedDateTime.now();
        return admin;
    }

    private String encryptPassword(String originPassword, String salt, int iterated_times) {
        MessageDigest digest = new SHA256.Digest();
        digest.update(salt.getBytes());
        byte[] encryptedPasswordBytes = new byte[0];
        for (int i = 0; i < iterated_times; i++) {
            encryptedPasswordBytes = digest.digest(digest.digest(originPassword.getBytes()));
        }
        return Arrays.toString(encryptedPasswordBytes);
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
