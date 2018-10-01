package com.javadeep.boot.validator.controller;

import com.javadeep.boot.validator.annotation.JavadeepValid;
import com.javadeep.boot.validator.form.UserForm;
import com.javadeep.boot.validator.group.Group1;
import com.javadeep.boot.validator.group.Group2;
import com.javadeep.boot.validator.validator.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @JavadeepValid(hibernateGroups = Group1.class)
    public void userForm(@SuppressWarnings("unused") UserForm userForm) {

    }

    @JavadeepValid(failFast = false, value = UserValidator.class, hibernateGroups = {Group1.class, Group2.class})
    public void userFormValid(@SuppressWarnings("unused") UserForm userForm) {

    }
}
