package com.javadeep.boot.common.validation.metadata;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ValidationResultTest {

    @Test
    public void testValidationResult() {
        ValidationResult result = ValidationResult.build()
                .addGlobalError("error")
                .addGlobalError(1, "error")
                .addError(ValidationError.of("id", "id is null"))
                .addErrors(ValidationError.of("id2", "id2 is null"))
                .addErrors(new ArrayList<ValidationError>() {{
                    add(ValidationError.of("id3", "id3 is null"));
                }});
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals(3, result.getErrors().size());
        Assert.assertEquals("error", result.getGlobalErrorMessage());
        Assert.assertEquals(1, result.getGlobalErrorCode());
    }
}
