package app.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author steve
 */
public class PasswordEncryptHelper {
    public static String encryptPassword(String originPassword, String salt, int iterated_times) throws PasswordEncryptException {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptException(e);
        }
        digest.update(salt.getBytes());
        byte[] encryptedPasswordBytes = new byte[0];
        for (int i = 0; i < iterated_times; i++) {
            encryptedPasswordBytes = digest.digest(digest.digest(originPassword.getBytes()));
        }
        return new String(encryptedPasswordBytes, StandardCharsets.UTF_8);
    }
}
