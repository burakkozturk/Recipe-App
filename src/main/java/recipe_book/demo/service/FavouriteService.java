package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.model.Favourite;
import recipe_book.demo.repository.FavouriteRepository;

import java.util.List;

@Service
public class FavouriteService {


    private final FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public void addFavourite(Long userId, Long recipeId) {
        if (favouriteRepository.existsByUserIdAndRecipeId(userId, recipeId)) {
            throw new IllegalArgumentException("This recipe is already in your favourites.");
        }
        Favourite favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setRecipeId(recipeId);
        favouriteRepository.save(favourite);
    }

    public void removeFavourite(Long userId, Long recipeId) {
        favouriteRepository.deleteByUserIdAndRecipeId(userId, recipeId);
    }

    public List<Favourite> getUserFavourites(Long userId) {
        return favouriteRepository.findByUserId(userId);
    }
}
