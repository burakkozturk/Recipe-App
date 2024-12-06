package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipe_book.demo.model.Recipe;


import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
