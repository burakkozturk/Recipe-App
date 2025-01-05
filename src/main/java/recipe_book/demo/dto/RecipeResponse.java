// RecipeResponse.java
package recipe_book.demo.dto;

import lombok.Data;
import recipe_book.demo.model.Ingredient;
import recipe_book.demo.model.Instruction;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private CategoryResponse category;
    private List<String> tags;
    private String imageUrl;
    private String difficulty;
    private Integer cookTime;
    private Integer prepTime;
    private UserResponse author;
    private Integer likes;

    @Data
    public static class CategoryResponse {
        private Long id;
        private String name;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String name;
        private String username;
        private String email;
        private String profilePhoto;
    }
}