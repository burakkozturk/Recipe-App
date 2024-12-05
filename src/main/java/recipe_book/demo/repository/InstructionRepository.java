package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction,Long> {
}
