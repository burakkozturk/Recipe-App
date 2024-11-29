package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

}
