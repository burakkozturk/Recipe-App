package recipe_book.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRecipeId(Long recipeId); // Belirli bir tarife ait yorumları getir

    List<Comment> findByParentId(Long parentId); // Belirli bir yorumun alt yorumlarını getir
}
