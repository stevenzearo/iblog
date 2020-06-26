package app.site.api;

import app.site.api.admin.CreateAdminAJAXRequest;
import app.web.error.WebException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface AdminAJAXWebService {
    @RequestMapping(value = "/ajax/admin", method = RequestMethod.POST)
    void create(CreateAdminAJAXRequest request) throws WebException;
}
