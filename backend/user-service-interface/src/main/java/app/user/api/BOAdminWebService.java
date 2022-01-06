package app.user.api;

import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.BOGetAdminByIdResponse;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient(name = "user-web-service", qualifiers = "admin-web-service")
public interface BOAdminWebService {
    @RequestMapping(value = "/bo/admin", method = RequestMethod.POST)
    EmptyResponse create(@RequestBody BOCreateAdminRequest request);

    @RequestMapping(value = "/bo/admin/{id}", method = RequestMethod.GET)
    Response<BOGetAdminByIdResponse> getById(@PathVariable("id") String id);

    @RequestMapping(value = "/bo/admin", method = RequestMethod.GET)
    Response<BOGetAdminByEmailResponse> getByEmail(@RequestParam(name = "email", required = true) String email);
}
