package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipe_book.demo.model.Recipe;
import recipe_book.demo.model.User;


import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryId(Long categoryId);

    List<Recipe> findByAuthor(User author);
    void deleteByAuthor(User author);

}
