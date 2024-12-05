package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
