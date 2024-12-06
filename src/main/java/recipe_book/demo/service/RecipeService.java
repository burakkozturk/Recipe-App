package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.dto.RecipeDto;
import recipe_book.demo.model.Category;
import recipe_book.demo.model.Ingredient;
import recipe_book.demo.model.Instruction;
import recipe_book.demo.model.Recipe;
import recipe_book.demo.repository.CategoryRepository;
import recipe_book.demo.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Recipe> getAllRecipes(){

        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList;
    }

    public Optional<Recipe> getRecipeById (Long recipeId){

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        return optionalRecipe;
    }

    public Recipe saveGenerally(RecipeDto recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());

        Category category = categoryRepository.findById(recipeDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        recipe.setCategory(category);

        recipe.setTags(recipeDTO.getTags());
        recipe.setImageUrl(recipeDTO.getImagery());
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());

        recipe.setAuthorId(recipeDTO.getAuthorId());

        return recipeRepository.save(recipe);
    }

    public Recipe saveIngredients(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }

        recipe.getIngredients().addAll(ingredients);
        return recipeRepository.save(recipe);
    }

    public Recipe saveInstructions(Long recipeId, List<Instruction> instructions) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (Instruction instruction : instructions) {
            instruction.setRecipe(recipe);
        }

        recipe.getInstructions().addAll(instructions);
        return recipeRepository.save(recipe);
    }
}
