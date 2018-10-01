package com.javadeep.boot.common.validation.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * 校验结果
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class ValidationResult {

    private List<ValidationError> errors = new ArrayList<>();
    private boolean success = true;
    private int globalErrorCode = 0;
    private String globalErrorMessage = "";

    private ValidationResult() {

    }

    /**
     * 初始化校验结果
     *
     * @return 返回初始化后的校验结果
     */
    public static ValidationResult build() {
        return new ValidationResult();
    }

    /**
     * 添加全局错误
     *
     * @param globalErrorCode    全局错误编码
     * @param globalErrorMessage 全局错误Message
     * @return 返回校验结果本身
     */
    public final ValidationResult addGlobalError(int globalErrorCode, String globalErrorMessage) {
        Objects.requireNonNull(globalErrorMessage, "globalErrorMessage is null");
        this.globalErrorCode = globalErrorCode;
        this.globalErrorMessage = globalErrorMessage;
        success = false;
        return this;
    }

    /**
     * 添加全局错误
     *
     * @param globalErrorMessage 全局错误Message
     * @return 返回校验结果本身
     */
    public final ValidationResult addGlobalError(String globalErrorMessage) {
        Objects.requireNonNull(globalErrorMessage, "globalErrorMessage is null");
        this.globalErrorMessage = globalErrorMessage;
        success = false;
        return this;
    }

    /**
     * 添加一个Error
     *
     * @param error ValidationError
     * @return 返回校验结果本身
     */
    public final ValidationResult addError(ValidationError error) {
        Objects.requireNonNull(error, "error is null");
        errors.add(error);
        success = false;
        return this;
    }

    /**
     * 批量添加Error（数组方式）
     *
     * @param errors ValidationError数组
     * @return 返回校验结果本身
     */
    public final ValidationResult addErrors(ValidationError... errors) {
        Objects.requireNonNull(errors, "errors is null");
        Arrays.stream(errors)
                .forEach(this::addError);
        return this;
    }

    /**
     * 批量添加Error（迭代器方式）
     *
     * @param errors ValidationError迭代器
     * @return 返回校验结果本身
     */
    public ValidationResult addErrors(Iterable<ValidationError> errors) {
        Objects.requireNonNull(errors, "errors is null");
        StreamSupport.stream(errors.spliterator(), false)
                .forEach(this::addError);
        return this;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getGlobalErrorCode() {
        return globalErrorCode;
    }

    public String getGlobalErrorMessage() {
        return globalErrorMessage;
    }
}
