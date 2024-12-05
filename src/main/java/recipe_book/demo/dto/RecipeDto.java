package recipe_book.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

    private String title;
    private String description;
    private Long categoryId;
    private List<String> tags;
    private String imagery;
    private String difficulty;
    private int cookTime;
    private int prepTime;

    private Long authorId;
}
