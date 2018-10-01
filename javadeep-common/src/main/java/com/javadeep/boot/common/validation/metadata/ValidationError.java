package com.javadeep.boot.common.validation.metadata;

import java.util.Objects;

/**
 * 校验结果错误信息
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class ValidationError {

    private final String field;
    private final String errorMsg;
    private final Object[] params;
    private final int errorCode;
    private final Object invalidValue;

    private ValidationError(String field, String errorMsg, Object[] params, int errorCode, Object invalidValue) {
        this.field = field;
        this.errorMsg = errorMsg;
        this.params = params;
        this.errorCode = errorCode;
        this.invalidValue = invalidValue;
    }

    public static ValidationError of(String field, String errorMsg, Object... params) {
        return builder(field, errorMsg, params).build();
    }

    public static Builder builder(String field, String errorMsg, Object... params) {
        return new Builder(field, errorMsg, params);
    }

    public static class Builder {
        private final String field;
        private final String errMsg;
        private final Object[] params;
        private int errorCode = 0;
        private Object invalidValue;

        private Builder(String field, String errMsg, Object[] params) {
            Objects.requireNonNull(field, "field is null");
            Objects.requireNonNull(errMsg, "errMsg is null");

            this.field = field;
            this.errMsg = errMsg;
            this.params = params;
        }

        public final Builder errorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public final Builder invalidValue(Object invalidValue) {
            this.invalidValue = invalidValue;
            return this;
        }

        public final ValidationError build() {
            return new ValidationError(field, errMsg, params, errorCode, invalidValue);
        }
    }

    public String getField() {
        return field;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Object[] getParams() {
        return params;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }
}
