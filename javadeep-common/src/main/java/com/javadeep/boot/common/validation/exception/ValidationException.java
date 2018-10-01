package com.javadeep.boot.common.validation.exception;

import com.javadeep.boot.common.validation.metadata.ValidationResult;

import java.util.Objects;

/**
 * 校验异常
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class ValidationException extends RuntimeException {

    private final ValidationResult result;

    public ValidationException(ValidationResult result) {
        super(Objects.requireNonNull(result, "result is null").getGlobalErrorMessage());
        this.result = result;
    }

    public ValidationResult getResult() {
        return result;
    }
}
