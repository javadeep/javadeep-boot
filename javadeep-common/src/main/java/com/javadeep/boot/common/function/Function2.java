package com.javadeep.boot.common.function;

import java.util.function.BiFunction;

/**
 * Function2, BiFunction的扩展
 *
 * @param <T> 第一个参数的类型
 * @param <U> 第二个参数的类型
 * @param <R> 返回值的类型
 */
@FunctionalInterface
public interface Function2<T, U, R> extends BiFunction<T, U, R> {
}
