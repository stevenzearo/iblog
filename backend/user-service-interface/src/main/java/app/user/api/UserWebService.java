package app.user.api;

import app.user.api.user.GetUserByEmailResponse;
import app.user.api.user.GetUserResponse;
import app.user.api.user.RegisterUserRequest;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient(value = "user-web-service", qualifier = "user-web-service")
public interface UserWebService {
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    EmptyResponse register(@RequestBody RegisterUserRequest request);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    Response<GetUserResponse> get(@PathVariable("id") Long id);

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    Response<GetUserByEmailResponse> getByEmail(@RequestBody String email);
}
