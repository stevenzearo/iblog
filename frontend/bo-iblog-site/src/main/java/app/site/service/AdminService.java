package app.site.service;

import app.site.web.ErrorCodes;
import app.site.web.SessionContext;
import app.user.PasswordEncryptException;
import app.user.PasswordEncryptHelper;
import app.user.api.BOAdminWebService;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.web.error.NotFoundException;
import app.web.error.WebException;
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
    @Autowired
    BOAdminWebService boAdminWebService;

    public boolean login(String email, String password, HttpServletRequest request) throws WebException {
        BOGetAdminByEmailResponse admin = boAdminWebService.getByEmail(email);
        if (admin == null) throw new NotFoundException(ErrorCodes.ADMIN_NOT_FOUND, String.format("admin not found, email = %s", email));
        String encryptedPassword = getEncryptedPassword(password, admin);
        if (!encryptedPassword.equals(admin.password)) return false;
        Admin sessionAdmin = buildSessionAdmin(admin);
        request.getSession().setAttribute(SessionContext.CURRENT_ADMIN, sessionAdmin);
        return true;
    }

    private String getEncryptedPassword(String password, BOGetAdminByEmailResponse admin) throws WebException {
        String encryptPassword = null;
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
