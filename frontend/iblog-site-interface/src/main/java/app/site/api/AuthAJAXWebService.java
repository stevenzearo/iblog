package app.site.api;

import app.web.error.WebException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface AuthAJAXWebService {
    @RequestMapping(value = "/ajax/auth", method = RequestMethod.GET)
    String getAuth() throws WebException;
}
