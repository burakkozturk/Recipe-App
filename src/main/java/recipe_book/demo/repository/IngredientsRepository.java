package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.Ingredient;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
}
