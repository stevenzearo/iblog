package app.user.api;

import app.user.api.user.GetUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient(value = "user-web-service", qualifier = "user-web-service")
public interface UserWebService {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    GetUserResponse get(@PathVariable("id") Long id);
}
