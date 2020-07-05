package app.site.service;

import app.site.api.admin.CreateAdminAJAXRequest;
import app.site.web.SessionContext;
import app.user.PasswordEncryptException;
import app.user.PasswordEncryptHelper;
import app.user.api.BOAdminWebService;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.web.error.WebException;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import app.web.session.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class AdminService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    @Autowired
    BOAdminWebService boAdminWebService;

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

    public boolean login(String email, String password, HttpServletRequest request) throws WebException {
        Response<BOGetAdminByEmailResponse> boResponse = boAdminWebService.getByEmail(email);
        BOGetAdminByEmailResponse data = ResponseHelper.fetchDataWithException(boResponse);
        String encryptedPassword = getEncryptedPassword(password, data);
        if (!encryptedPassword.equals(data.password)) return false;
        Admin sessionAdmin = buildSessionAdmin(data);
        request.getSession().setAttribute(SessionContext.CURRENT_ADMIN, sessionAdmin);
        return true;
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

    private Admin buildSessionAdmin(BOGetAdminByEmailResponse admin) {
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
}
