package recipe_book.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UsernameConstraintsValidator.class)
public @interface Username {

    String message() default "Invalid username!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}