package app.site.api;

import app.site.service.AuthService;
import app.web.error.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class AuthAJAXWebServiceImpl implements AuthAJAXWebService {
    private final Logger logger = LoggerFactory.getLogger(AuthAJAXWebServiceImpl.class);
    @Autowired
    AuthService authService;

    @Override
    public String getAuth() throws WebException {
        String auth = authService.createAuth();
        logger.info(String.format("created auth, auth=%s", auth));
        return auth;
    }
}
