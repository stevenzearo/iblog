package app.site.api;

import app.site.api.user.RegisterUserAJAXRequest;
import app.web.error.WebException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface UserAJAXWebService {
    @RequestMapping(value = "/ajax/user", method = RequestMethod.POST)
    void register(@RequestBody RegisterUserAJAXRequest request) throws WebException;
}
