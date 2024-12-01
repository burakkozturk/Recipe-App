package recipe_book.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.demo.model.VerificationCode;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, UUID> {
    Optional<VerificationCode> findByEmailAndCode(String email, String code);
    void deleteByExpirationTimeBefore(LocalDateTime now);
}