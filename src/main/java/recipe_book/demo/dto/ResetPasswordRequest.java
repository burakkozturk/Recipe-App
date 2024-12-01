package recipe_book.demo.dto;

import recipe_book.demo.validation.Password;

public record ResetPasswordRequest(
        String username,
        @Password
        String newPassword
) {
}
