package recipe_book.demo.validation;

import org.passay.*;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UsernameConstraintsValidator implements ConstraintValidator<Username, String> { // Password yerine Username yazdık

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) { // password yerine username

        PasswordValidator usernameValidator = new PasswordValidator(
                Arrays.asList(
                        // Length rule: Min 5, Max 15 characters
                        new LengthRule(5, 15),

                        // No whitespace allowed
                        new WhitespaceRule()
                )
        );

        RuleResult result = usernameValidator.validate(new PasswordData(username)); // password yerine username

        if (result.isValid()) {
            return true;
        }

        // Sending one message each time validation fails.
        constraintValidatorContext.buildConstraintViolationWithTemplate(
                usernameValidator.getMessages(result).stream().findFirst().orElse("Invalid username!")
        ).addConstraintViolation().disableDefaultConstraintViolation();

        return false;
    }
}
