package com.javadeep.boot.validator.test;

import com.javadeep.boot.common.validation.exception.ValidationException;
import com.javadeep.boot.validator.BaseTest;
import com.javadeep.boot.validator.controller.UserController;
import com.javadeep.boot.validator.form.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends BaseTest {

    @Autowired
    private UserController userController;

    @Test
    public void testUserForm_success() {
        UserForm userForm = new UserForm();
        userForm.setId(10);
        userForm.setName("123456789011");
        userController.userForm(userForm);
    }

    @Test(expected = ValidationException.class)
    public void testUserForm_fail() {
        UserForm userForm = new UserForm();
        userForm.setId(101);
        userForm.setName("123456789");
        userController.userForm(userForm);
    }

    @Test
    public void testUserFormValid() {
        UserForm userForm = new UserForm();
        userForm.setId(101);
        userForm.setName("1234567891011");
        try {
            userController.userFormValid(userForm);
        } catch (ValidationException e) {
            Assert.assertEquals(3, e.getResult().getErrors().size());
        }
    }
}
