package recipe_book.demo.dto;

import recipe_book.demo.model.Role;
import recipe_book.demo.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime lastLogin,
        LocalDate dateOfBirth,
        boolean isVerify,
        String profilePhoto,
        String biography,
        int followersCount,
        int followingCount,
        Set<Role> authorities
) {
    // Static factory method to convert User to UserResponse
    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getLastLogin(),
                user.getDateOfBirth(),
                user.isVerify(),
                user.getProfilePhoto(),
                user.getBiography(),
                user.getFollowersCount(),
                user.getFollowingCount(),
                user.getAuthorities()
        );
    }
}