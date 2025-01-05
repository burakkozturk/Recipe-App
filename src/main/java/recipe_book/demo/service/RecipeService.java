package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe_book.demo.dto.RecipeDto;
import recipe_book.demo.dto.RecipeResponse;
import recipe_book.demo.model.*;
import recipe_book.demo.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientsRepository ingredientsRepository;
    private final InstructionRepository instructionRepository;
    private final UserService userService;

    public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
                         IngredientsRepository ingredientsRepository, InstructionRepository instructionRepository,
                         UserRepository userRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.instructionRepository = instructionRepository;
        this.userService = userService;
    }

    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RecipeResponse getRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        return convertToResponse(recipe);
    }

    public List<RecipeResponse> getRecipesByCategoryId(Long categoryId) {
        return recipeRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<RecipeResponse> getRecipesByUsername(String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return recipeRepository.findByAuthor(user).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RecipeResponse saveGenerally(RecipeDto recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());

        Category category = categoryRepository.findById(recipeDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        recipe.setCategory(category);

        User author = userService.getUserById(recipeDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        recipe.setAuthor(author);

        recipe.setTags(recipeDTO.getTags());
        recipe.setImageUrl(recipeDTO.getImagery());
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());

        Recipe savedRecipe = recipeRepository.save(recipe);
        return convertToResponse(savedRecipe);
    }

    public RecipeResponse saveIngredients(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }

        recipe.getIngredients().addAll(ingredients);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return convertToResponse(savedRecipe);
    }

    public RecipeResponse saveInstructions(Long recipeId, List<Instruction> instructions) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (Instruction instruction : instructions) {
            instruction.setRecipe(recipe);
        }

        recipe.getInstructions().addAll(instructions);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return convertToResponse(savedRecipe);
    }

    public RecipeResponse updateRecipe(Long recipeId, RecipeDto recipeDTO) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

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

        Recipe updatedRecipe = recipeRepository.save(recipe);
        return convertToResponse(updatedRecipe);
    }

    public RecipeResponse updateIngredients(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        recipe.getIngredients().clear();
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }

        recipe.getIngredients().addAll(ingredients);
        Recipe updatedRecipe = recipeRepository.save(recipe);
        return convertToResponse(updatedRecipe);
    }

    public RecipeResponse updateInstructions(Long recipeId, List<Instruction> instructions) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        recipe.getInstructions().clear();
        for (Instruction instruction : instructions) {
            instruction.setRecipe(recipe);
        }

        recipe.getInstructions().addAll(instructions);
        Recipe updatedRecipe = recipeRepository.save(recipe);
        return convertToResponse(updatedRecipe);
    }

    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Transactional
    public void deleteAllRecipesByUserId(Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        recipeRepository.deleteByAuthor(user);
    }

    private RecipeResponse convertToResponse(Recipe recipe) {
        RecipeResponse response = new RecipeResponse();

        response.setId(recipe.getId());
        response.setTitle(recipe.getTitle());
        response.setDescription(recipe.getDescription());
        response.setCreatedAt(recipe.getCreatedAt());
        response.setUpdatedAt(recipe.getUpdatedAt());
        response.setIngredients(recipe.getIngredients());
        response.setInstructions(recipe.getInstructions());
        response.setTags(recipe.getTags());
        response.setImageUrl(recipe.getImageUrl());
        response.setDifficulty(recipe.getDifficulty());
        response.setCookTime(recipe.getCookTime());
        response.setPrepTime(recipe.getPrepTime());
        response.setLikes(recipe.getLikes());

        if (recipe.getCategory() != null) {
            RecipeResponse.CategoryResponse categoryResponse = new RecipeResponse.CategoryResponse();
            categoryResponse.setId(recipe.getCategory().getId());
            categoryResponse.setName(recipe.getCategory().getName());
            response.setCategory(categoryResponse);
        }

        if (recipe.getAuthor() != null) {
            RecipeResponse.UserResponse userResponse = new RecipeResponse.UserResponse();
            userResponse.setId(recipe.getAuthor().getId());
            userResponse.setName(recipe.getAuthor().getName());
            userResponse.setUsername(recipe.getAuthor().getUsername());
            userResponse.setEmail(recipe.getAuthor().getEmail());
            userResponse.setProfilePhoto(recipe.getAuthor().getProfilePhoto());
            response.setAuthor(userResponse);
        }

        return response;
    }
}