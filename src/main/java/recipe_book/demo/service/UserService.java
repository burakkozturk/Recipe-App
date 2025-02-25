package recipe_book.demo.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipe_book.demo.dto.UpdateUserDetailsRequest;
import recipe_book.demo.dto.UserResponse;
import recipe_book.demo.model.User;
import recipe_book.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public ResponseEntity<UserResponse> updateUserDetails(Long userId, UpdateUserDetailsRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(request.getEmail())) {
            userRepository.findByEmail(request.getEmail())
                    .ifPresent(existingUser -> {
                        throw new IllegalArgumentException("Email already exists!");
                    });
        }

        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setProfilePhoto(request.getProfilePhoto());
        user.setBiography(request.getBiography());
        user.setCreatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(UserResponse.fromUser(updatedUser), HttpStatus.OK);
    }

    public void resetPassword(String username, String newPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }
}