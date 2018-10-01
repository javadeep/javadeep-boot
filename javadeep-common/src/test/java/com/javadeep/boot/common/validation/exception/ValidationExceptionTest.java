package com.javadeep.boot.common.validation.exception;

import org.junit.Test;

public class ValidationExceptionTest {

    @Test(expected = NullPointerException.class)
    public void testValidationExcepiton() {
        throw new ValidationException(null);
    }
}
