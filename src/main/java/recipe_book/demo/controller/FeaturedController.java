package recipe_book.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.FeaturedCategories;
import recipe_book.demo.model.FeaturedRecipes;
import recipe_book.demo.service.FeaturedService;

@RestController
@RequestMapping("/api/featured")
public class FeaturedController {

    private final FeaturedService featuredService;

    public FeaturedController(FeaturedService featuredService) {
        this.featuredService = featuredService;
    }

    @GetMapping("/categories")
    public ResponseEntity<FeaturedCategories> getFeaturedCategories() {
        return ResponseEntity.ok(featuredService.getFeaturedCategories());
    }

    @GetMapping("/recipes")
    public ResponseEntity<FeaturedRecipes> getFeaturedRecipes() {
        return ResponseEntity.ok(featuredService.getFeaturedRecipes());
    }

    @PutMapping("/categories")
    public ResponseEntity<FeaturedCategories> updateFeaturedCategories(
            @RequestBody FeaturedCategories featuredCategories) {
        return ResponseEntity.ok(featuredService.updateFeaturedCategories(featuredCategories));
    }

    @PutMapping("/recipes")
    public ResponseEntity<FeaturedRecipes> updateFeaturedRecipes(
            @RequestBody FeaturedRecipes featuredRecipes) {
        return ResponseEntity.ok(featuredService.updateFeaturedRecipes(featuredRecipes));
    }
}