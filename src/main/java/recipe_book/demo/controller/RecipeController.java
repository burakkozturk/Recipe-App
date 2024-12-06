package recipe_book.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.dto.RecipeDto;
import recipe_book.demo.model.Ingredient;
import recipe_book.demo.model.Instruction;
import recipe_book.demo.model.Recipe;
import recipe_book.demo.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAllRecipes(){

        List<Recipe> recipeList = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Recipe>> getRecipeById(@PathVariable Long id){

        Optional<Recipe> optionalRecipe = recipeService.getRecipeById(id);
        return new ResponseEntity<>(optionalRecipe, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Recipe> saveGenerally(@RequestBody RecipeDto recipeDTO) {
        Recipe savedRecipe = recipeService.saveGenerally(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<Recipe> saveIngredients(@PathVariable Long id, @RequestBody List<Ingredient> ingredients) {
        Recipe updatedRecipe = recipeService.saveIngredients(id, ingredients);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PostMapping("/{id}/instructions")
    public ResponseEntity<Recipe> saveInstructions(@PathVariable Long id, @RequestBody List<Instruction> instructions) {
        Recipe updatedRecipe = recipeService.saveInstructions(id, instructions);
        return ResponseEntity.ok(updatedRecipe);
    }

}
