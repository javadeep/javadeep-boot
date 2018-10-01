package com.javadeep.boot.validator.validator;

import com.javadeep.boot.common.validation.metadata.ValidationError;
import com.javadeep.boot.common.validation.metadata.ValidationResult;
import com.javadeep.boot.validator.annotation.JavadeepValidHandler;
import com.javadeep.boot.validator.form.UserForm;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @JavadeepValidHandler
    @SuppressWarnings("unused")
    public void userFormValid(ValidationResult result, UserForm userForm) {
        if (userForm != null) {
            result.addError(ValidationError.of("userForm", "userFormInvalid"));
        }
    }
}
