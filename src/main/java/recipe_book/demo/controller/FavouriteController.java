package recipe_book.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.Favourite;
import recipe_book.demo.service.FavouriteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {


    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping("")
    public ResponseEntity<String> addFavourite(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long recipeId = request.get("recipeId");
        favouriteService.addFavourite(userId, recipeId);
        return ResponseEntity.ok("Recipe added to favourites.");
    }

    @DeleteMapping("")
    public ResponseEntity<String> removeFavourite(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long recipeId = request.get("recipeId");
        favouriteService.removeFavourite(userId, recipeId);
        return ResponseEntity.ok("Recipe removed from favourites.");
    }

    // NOT BODY JUST ID

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favourite>> getUserFavourites(@PathVariable Long userId) {
        List<Favourite> favourites = favouriteService.getUserFavourites(userId);
        return ResponseEntity.ok(favourites);
    }

    @GetMapping("/recipe/{recipeId}/users")
    public ResponseEntity<List<Long>> getUsersByRecipeId(@PathVariable Long recipeId) {
        List<Long> userIds = favouriteService.getUsersByRecipeId(recipeId);
        return ResponseEntity.ok(userIds);
    }


    //This recipe is already in your favourites. !!!!!!!!!! EXCEPTION
}
