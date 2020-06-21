package app.user.api;

import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.role.BOCreateRoleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient(name = "admin-web-service")
public interface BOAdminWebService {
    @RequestMapping(value = "/bo/admin", method = RequestMethod.POST)
    void create(BOCreateAdminRequest request);

    @RequestMapping(value = "/bo/admin", method = RequestMethod.GET)
    BOGetAdminByEmailResponse getByEmail(@RequestParam("email") String email);
}
