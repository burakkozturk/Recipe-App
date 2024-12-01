package recipe_book.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.dto.ResetPasswordRequest;
import recipe_book.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService passwordResetService) {
        this.userService = passwordResetService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(request.username(), request.newPassword());
        return ResponseEntity.ok("Password reset successful!");
    }
}
