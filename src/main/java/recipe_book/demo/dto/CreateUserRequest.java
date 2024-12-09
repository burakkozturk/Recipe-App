package recipe_book.demo.dto;


import lombok.Builder;
import recipe_book.demo.model.Role;
import recipe_book.demo.validation.Password;
import recipe_book.demo.validation.Username;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        @Username
        String username,
        @Password
        String password,
        Set<Role> authorities
){
}