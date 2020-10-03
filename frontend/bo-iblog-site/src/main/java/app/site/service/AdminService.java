package app.site.service;

import app.site.api.admin.CreateAdminAJAXRequest;
import app.site.cache.AdminCache;
import app.site.web.ErrorCodes;
import app.site.cache.Admin;
import app.util.PasswordEncryptException;
import app.util.PasswordEncryptHelper;
import app.user.api.BOAdminWebService;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.BOGetAdminByIdResponse;
import app.web.error.ConflictException;
import app.web.error.WebException;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class AdminService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    @Autowired(required = false)
    BOAdminWebService boAdminWebService;
    @Autowired
    AdminCache adminCache;
    @Autowired
    AuthService authService;

    public void create(CreateAdminAJAXRequest request) throws WebException {
        BOCreateAdminRequest boRequest = new BOCreateAdminRequest();
        boRequest.groupId = request.groupId;
        boRequest.name = request.name;
        boRequest.email = request.email;
        boRequest.password = request.password;
        boRequest.requestedBy = REQUESTED_BY;
        EmptyResponse response = boAdminWebService.create(boRequest);
        ResponseHelper.checkStatusCode(response);
    }

    public Admin getCurrent(String auth) throws ConflictException {
        String adminId = authService.getAuthedAdminId(auth);
        return adminCache.findById(adminId).orElseGet(() -> {
            BOGetAdminByIdResponse data = boAdminWebService.getById(adminId).data;
            Admin admin = buildAdminCache(data);
            adminCache.save(admin);
            return admin;
        });
    }

    public Admin login(String email, String password, String auth) throws WebException {
        Optional<Admin> adminOptional = adminCache.findByEmail(email);
        if (adminOptional.isPresent()) {
            throw new ConflictException(ErrorCodes.ADMIN_ALREADY_LOGIN, "admin already login, no need login again");
        }

        Response<BOGetAdminByEmailResponse> boResponse = boAdminWebService.getByEmail(email);
        BOGetAdminByEmailResponse data = ResponseHelper.fetchDataWithException(boResponse);
        String encryptedPassword = getEncryptedPassword(password, data);
        if (!encryptedPassword.equals(data.password))
            throw new ConflictException(ErrorCodes.LOGIN_FAILED, "login failed, please your email and password.");
        Admin admin = buildAdminCache(data);
        adminCache.save(admin);
        authService.authAdmin(auth, admin.id);
        return admin;
    }

    public void logout(String auth) throws ConflictException {
        String adminId = authService.getAuthedAdminId(auth);
        authService.invalid(auth);
        adminCache.deleteById(adminId);
    }

    private String getEncryptedPassword(String password, BOGetAdminByEmailResponse admin) throws WebException {
        String encryptPassword;
        try {
            encryptPassword = PasswordEncryptHelper.encryptPassword(password, admin.salt, admin.iteratedTimes);
        } catch (PasswordEncryptException e) {
            throw new WebException(e.getMessage());
        }
        return encryptPassword;
    }

    private Admin buildAdminCache(BOGetAdminByEmailResponse admin) {
        Admin currentAdmin = new Admin();
        currentAdmin.id = admin.id;
        currentAdmin.name = admin.name;
        currentAdmin.email = admin.email;
        Admin.Group group = new Admin.Group();
        group.id = admin.group.id;
        group.name = admin.group.name;
        group.roles = admin.group.roles.stream().map(roleView -> {
            Admin.Role role = new Admin.Role();
            role.name = roleView.name;
            role.authority = roleView.authority;
            return role;
        }).collect(Collectors.toList());
        currentAdmin.group = group;
        return currentAdmin;
    }

    private Admin buildAdminCache(BOGetAdminByIdResponse boResponse) {
        Admin admin = new Admin();
        admin.id = boResponse.id;
        admin.name = boResponse.name;
        admin.email = boResponse.email;
        Admin.Group group = new Admin.Group();
        group.id = boResponse.group.id;
        group.name = boResponse.group.name;
        group.roles = boResponse.group.roles.stream().map(role -> {
            Admin.Role roleCache = new Admin.Role();
            roleCache.name = role.name;
            roleCache.authority = role.authority;
            return roleCache;
        }).collect(Collectors.toList());
        admin.group = group;
        return admin;
    }
}
