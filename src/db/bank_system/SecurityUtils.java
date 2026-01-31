package db.bank_system;
import java.security.*;
public class SecurityUtils {
    public static String hashpassword(String password) {
        try {
            MessageDigest d = MessageDigest.getInstance("SHA-256");
            byte[] eh = d.digest(password.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : eh) {
                String hc = Integer.toHexString(0xff & b);
                if (hc.length() == 1) hex.append('0');
                hex.append(hc);
            }
            return hex.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
