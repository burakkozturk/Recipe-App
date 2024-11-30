package recipe_book.demo.dto;


import lombok.Builder;
import recipe_book.demo.model.Role;
import recipe_book.demo.validation.Password;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String username,
        @Password
        String password,
        Set<Role> authorities
){
}