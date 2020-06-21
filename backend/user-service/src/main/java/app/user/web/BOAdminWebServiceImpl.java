package app.user.web;

import app.user.api.BOAdminWebService;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.service.BOAdminService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author steve
 */
public class BOAdminWebServiceImpl implements BOAdminWebService {
    @Autowired
    BOAdminService boAdminService;
    @Override
    public void create(BOCreateAdminRequest request) {

    }

    @Override
    public BOGetAdminByEmailResponse getByEmail(String email) {
        return null;
    }
}
