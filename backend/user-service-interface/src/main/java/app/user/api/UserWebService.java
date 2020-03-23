package app.user.api;

import app.user.api.user.GetUserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@RequestMapping("/user")
public interface UserWebService {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GetUserResponse get(@PathVariable("id") Integer id);
}
