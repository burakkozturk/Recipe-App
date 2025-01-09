package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.model.FeaturedCategories;
import recipe_book.demo.model.FeaturedRecipes;
import recipe_book.demo.repository.FeaturedCagetoryRepository;
import recipe_book.demo.repository.FeaturedRecipesRepository;


@Service
public class FeaturedService {

    private final FeaturedRecipesRepository featuredRecipesRepository;
    private final FeaturedCagetoryRepository featuredCagetoryRepository;

    public FeaturedService(FeaturedRecipesRepository featuredRecipesRepository, FeaturedCagetoryRepository featuredCagetoryRepository) {
        this.featuredRecipesRepository = featuredRecipesRepository;
        this.featuredCagetoryRepository = featuredCagetoryRepository;
    }

    public FeaturedCategories getFeaturedCategories() {
        return featuredCagetoryRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Featured categories not found"));
    }

    public FeaturedRecipes getFeaturedRecipes() {
        return featuredRecipesRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Featured recipes not found"));
    }

    public FeaturedCategories updateFeaturedCategories(FeaturedCategories featuredCategories) {
        // Since we only want one record, we'll delete existing ones first
        featuredCagetoryRepository.deleteAll();
        return featuredCagetoryRepository.save(featuredCategories);
    }

    public FeaturedRecipes updateFeaturedRecipes(FeaturedRecipes featuredRecipes) {
        // Since we only want one record, we'll delete existing ones first
        featuredRecipesRepository.deleteAll();
        return featuredRecipesRepository.save(featuredRecipes);
    }
}
