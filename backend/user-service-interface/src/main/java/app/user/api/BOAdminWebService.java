package app.user.api;

import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.web.error.WebException;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient(name = "user-web-service", qualifier = "admin-web-service")
public interface BOAdminWebService {
    @RequestMapping(value = "/bo/admin", method = RequestMethod.POST)
    Response<Object> create(@RequestBody BOCreateAdminRequest request) throws WebException;

    @RequestMapping(value = "/bo/admin", method = RequestMethod.GET)
    Response<BOGetAdminByEmailResponse> getByEmail(@RequestParam("email") String email);
}
