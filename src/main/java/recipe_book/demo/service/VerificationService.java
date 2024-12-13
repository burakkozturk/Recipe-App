package recipe_book.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_book.demo.core.VerificationCodeGenerator;
import recipe_book.demo.model.User;
import recipe_book.demo.model.VerificationCode;
import recipe_book.demo.repository.UserRepository;
import recipe_book.demo.repository.VerificationCodeRepository;

import java.time.LocalDateTime;

@Service
public class VerificationService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private EmailService emailService;

    private final UserRepository userRepository;

    public VerificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendAndSaveVerificationCode(String email) {
        String code = VerificationCodeGenerator.generateCode();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setExpirationTime(LocalDateTime.now().plusMinutes(5)); // 5 dakika geçerli

        verificationCodeRepository.save(verificationCode);
        emailService.sendVerificationCode(email, code);
    }


}
