package recipe_book.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.VerificationCode;
import recipe_book.demo.repository.VerificationCodeRepository;
import recipe_book.demo.service.VerificationCodeService;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        verificationCodeService.sendAndSaveVerificationCode(email);
        return ResponseEntity.ok("Verification code sent to: " + email);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String inputCode) {
        Optional<VerificationCode> verification = verificationCodeRepository.findByEmailAndCode(email, inputCode);

        if (verification.isPresent() && verification.get().getExpirationTime().isAfter(LocalDateTime.now())) {
            return ResponseEntity.ok("Verification successful!");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired verification code.");
        }
    }
}
