package com.javadeep.boot.common.function;

import java.util.function.BinaryOperator;

/**
 * Operator2（BinaryOperator扩展）
 *
 * @author javadeep
 * @since 1.0.0
 */
public interface Operator2<T> extends BinaryOperator<T> {

    /**
     * 取左值
     *
     * @param <T> 数据的类型
     * @return 返回两值的左值
     */
    static <T> BinaryOperator<T> left() {
        return (a, b) -> a;
    }

    /**
     * 取右值
     *
     * @param <T> 数据的类型
     * @return 返回两值的右值
     */
    static <T> BinaryOperator<T> right() {
        return (a, b) -> b;
    }
}
