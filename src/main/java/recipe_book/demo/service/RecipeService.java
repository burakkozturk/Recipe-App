package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe_book.demo.dto.RecipeDto;
import recipe_book.demo.model.*;
import recipe_book.demo.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientsRepository ingredientsRepository;

    private final InstructionRepository instructionRepository;
    private final UserService userService ;

    public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository, IngredientsRepository ingredientsRepository, InstructionRepository instructionRepository, UserRepository userRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.instructionRepository = instructionRepository;
        this.userService = userService;
    }

    public List<Recipe> getAllRecipes(){

        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList;
    }

    public Optional<Recipe> getRecipeById (Long recipeId){

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        return optionalRecipe;
    }

    public List<Recipe> getProductByCategoryId(Long categoryId){
        List<Recipe> list = recipeRepository.findByCategoryId(categoryId);
        return list;
    }

    public List<Recipe> getRecipesByUsername(String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return recipeRepository.findByAuthorId(user.getId());
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

    public Recipe updateRecipe(Long recipeId, RecipeDto recipeDTO) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        // Recipe alanlarını güncelle
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
        recipe.setUpdatedAt(LocalDateTime.now());

        return recipeRepository.save(recipe);
    }

    public Recipe updateIngredients(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        // Mevcut malzemeleri temizle ve yenileri ekle
        recipe.getIngredients().clear();
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }

        recipe.getIngredients().addAll(ingredients);
        return recipeRepository.save(recipe);
    }

    public Recipe updateInstructions(Long recipeId, List<Instruction> instructions) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        // Mevcut talimatları temizle ve yenileri ekle
        recipe.getInstructions().clear();
        for (Instruction instruction : instructions) {
            instruction.setRecipe(recipe);
        }

        recipe.getInstructions().addAll(instructions);
        return recipeRepository.save(recipe);
    }



    public void deleteRecipe(Long recipeId){
        recipeRepository.deleteById(recipeId);

    }

    @Transactional
    public void deleteAllRecipesByUserId(Long userId){
        recipeRepository.findByAuthorId(userId);
    }
}
