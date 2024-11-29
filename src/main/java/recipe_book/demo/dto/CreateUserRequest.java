package recipe_book.demo.dto;


import lombok.Builder;
import recipe_book.demo.model.Role;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
){
}