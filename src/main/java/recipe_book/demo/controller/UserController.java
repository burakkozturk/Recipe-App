package recipe_book.demo.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.dto.ResetPasswordRequest;
import recipe_book.demo.dto.UpdateUserDetailsRequest;
import recipe_book.demo.model.User;
import recipe_book.demo.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService passwordResetService) {
        this.userService = passwordResetService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId){
        Optional<User> user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id, @RequestBody UpdateUserDetailsRequest request) {
        try {
            // UserService'den gelen yanıtı döndür
            return userService.updateUserDetails(id, request);
        } catch (RuntimeException e) {
            // Hata durumunda, 404 Not Found döndürülür
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(request.username(), request.newPassword());
        return ResponseEntity.ok("Password reset successful!");
    }


}
