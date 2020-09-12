package app.site.api;

import app.site.api.admin.CreateAdminAJAXRequest;
import app.site.service.AdminService;
import app.site.web.interceptor.AuthRequired;
import app.site.web.interceptor.LoginRequired;
import app.web.error.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
@LoginRequired
public class AdminAJAXWebServiceImpl implements AdminAJAXWebService {
    @Autowired
    AdminService adminService;

    @Override
    public void create(CreateAdminAJAXRequest request) throws WebException {
        adminService.create(request);
    }
}
