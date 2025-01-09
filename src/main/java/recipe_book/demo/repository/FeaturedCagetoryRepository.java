package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipe_book.demo.model.FeaturedCategories;

@Repository
public interface FeaturedCagetoryRepository extends JpaRepository<FeaturedCategories, Long> {
}
