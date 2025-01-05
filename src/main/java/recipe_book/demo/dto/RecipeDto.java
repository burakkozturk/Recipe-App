package recipe_book.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String title;
    private String description;
    private Long categoryId;
    private String imagery;
    private String difficulty;
    private Integer cookTime;
    private Integer prepTime;
    private Long authorId;
    private List<String> tags;
}

