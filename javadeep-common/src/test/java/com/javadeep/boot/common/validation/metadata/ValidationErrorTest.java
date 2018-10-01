package com.javadeep.boot.common.validation.metadata;

import org.junit.Assert;
import org.junit.Test;

public class ValidationErrorTest {

    @Test
    public void testValidationError_build() {
        ValidationError validationError = ValidationError.builder("id", "id is not null")
                .errorCode(1)
                .invalidValue(null)
                .build();
        Assert.assertEquals("id", validationError.getField());
        Assert.assertEquals("id is not null", validationError.getErrorMsg());
        Assert.assertArrayEquals(new Object[]{}, validationError.getParams());
        Assert.assertEquals(1, validationError.getErrorCode());
        Assert.assertNull(validationError.getInvalidValue());
    }

    @Test
    public void testValidationError_of() {
        ValidationError validationError = ValidationError.of("id", "id is not null");
        Assert.assertEquals("id", validationError.getField());
        Assert.assertEquals("id is not null", validationError.getErrorMsg());
        Assert.assertArrayEquals(new Object[]{}, validationError.getParams());
        Assert.assertEquals(0, validationError.getErrorCode());
        Assert.assertNull(validationError.getInvalidValue());
    }
}
