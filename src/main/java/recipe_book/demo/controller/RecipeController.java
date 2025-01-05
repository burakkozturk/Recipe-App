package recipe_book.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.dto.RecipeDto;
import recipe_book.demo.dto.RecipeResponse;
import recipe_book.demo.model.Ingredient;
import recipe_book.demo.model.Instruction;
import recipe_book.demo.model.Recipe;
import recipe_book.demo.service.RecipeService;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        List<RecipeResponse> recipeList = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        RecipeResponse recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RecipeResponse>> getRecipesByCategoryId(@PathVariable Long categoryId){
        List<RecipeResponse> recipeList = recipeService.getRecipesByCategoryId(categoryId);
        return ResponseEntity.ok(recipeList);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<RecipeResponse>> getRecipesByUsername(@PathVariable String username) {
        List<RecipeResponse> recipes = recipeService.getRecipesByUsername(username);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/save")
    public ResponseEntity<RecipeResponse> saveGenerally(@RequestBody RecipeDto recipeDTO) {
        RecipeResponse savedRecipe = recipeService.saveGenerally(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<RecipeResponse> saveIngredients(@PathVariable Long id, @RequestBody List<Ingredient> ingredients) {
        RecipeResponse updatedRecipe = recipeService.saveIngredients(id, ingredients);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PostMapping("/{id}/instructions")
    public ResponseEntity<RecipeResponse> saveInstructions(@PathVariable Long id, @RequestBody List<Instruction> instructions) {
        RecipeResponse updatedRecipe = recipeService.saveInstructions(id, instructions);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable Long id, @RequestBody RecipeDto recipeDTO) {
        RecipeResponse updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<RecipeResponse> updateIngredients(@PathVariable Long id, @RequestBody List<Ingredient> ingredients) {
        RecipeResponse updatedRecipe = recipeService.updateIngredients(id, ingredients);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PutMapping("/{id}/instructions")
    public ResponseEntity<RecipeResponse> updateInstructions(@PathVariable Long id, @RequestBody List<Instruction> instructions) {
        RecipeResponse updatedRecipe = recipeService.updateInstructions(id, instructions);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<String> deleteRecipesByUserId(@PathVariable Long userId) {
        recipeService.deleteAllRecipesByUserId(userId);
        return ResponseEntity.ok("All recipes by user deleted successfully");
    }
}