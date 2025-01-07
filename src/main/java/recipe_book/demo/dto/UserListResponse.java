package recipe_book.demo.dto;

import recipe_book.demo.model.User;
import java.util.List;

public record UserListResponse(
        List<UserResponse> users,
        int totalUsers
) {
    public static UserListResponse fromUsers(List<User> users) {
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::fromUser)
                .toList();
        return new UserListResponse(userResponses, userResponses.size());  // Düzeltilmiş constructor çağrısı
    }
}