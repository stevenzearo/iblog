package app.user.admin;

import app.user.PasswordEncryptException;
import app.user.PasswordEncryptHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author steve
 */
public class PasswordEncryptTest {
    @Test
    void test() throws PasswordEncryptException {
        String originPassword = "hello,world,hello,world,hello,world,hello,world,hello,world,hello,world,";
        String originPassword2 = "hello,world,hello,world,hello,world,hello,world,hello,world,hello,world,";
        String encryptPassword = PasswordEncryptHelper.encryptPassword(originPassword, "a", 10);
        String encryptPassword2 = PasswordEncryptHelper.encryptPassword(originPassword2, "a", 10);
        Assertions.assertEquals(encryptPassword, encryptPassword2);
    }
}
