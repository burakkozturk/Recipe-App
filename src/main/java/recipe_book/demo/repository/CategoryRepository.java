package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipe_book.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
