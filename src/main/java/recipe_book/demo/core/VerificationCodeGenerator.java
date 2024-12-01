package recipe_book.demo.core;


import java.security.SecureRandom;

public class VerificationCodeGenerator {
    public static String generateCode() {
        SecureRandom random = new SecureRandom();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }
}