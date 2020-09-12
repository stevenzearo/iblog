package app.site.service;

import app.IntegrationTest;
import app.web.error.ConflictException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author steve
 */
public class AuthServiceTest extends IntegrationTest {
    @Autowired
    AuthService authService;

    @Ignore
    @Test
    public void createAuth() throws ConflictException {
        String auth = authService.createAuth();
        boolean isValid = authService.isValid(auth);
        boolean isExpired = authService.isExpired(auth);
        Assertions.assertTrue(isValid);
        Assertions.assertFalse(isExpired);
        authService.expire(auth);
        isExpired = authService.isExpired(auth);
        Assertions.assertTrue(isExpired);
        auth = authService.createAuth();
        authService.invalidAll();
        isValid = authService.isValid(auth);
        Assertions.assertFalse(isValid);
        String finalAuth = auth;
        Assertions.assertThrows(ConflictException.class, () -> authService.isExpired(finalAuth));
    }
}