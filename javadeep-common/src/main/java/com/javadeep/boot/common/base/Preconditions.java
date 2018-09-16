package com.javadeep.boot.common.base;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 参数检查工具
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    /**
     * 检查表达式是否为true，为false则抛出异常
     *
     * @param expression 表达式
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 检查表达式是否为true，为false则抛出异常，并带上message
     *
     * @param expression 表达式
     * @param message 检查失败时抛出异常的消息
     */
    public static void checkArgument(boolean expression, String message) {
        Objects.requireNonNull(message);
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检查表达式是否为true，为false则抛出异常，并带上message构造器
     *
     * @param expression 表达式
     * @param messageSupplier 检查失败时抛出异常的消息构造器
     */
    public static void checkArgument(boolean expression, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        if (!expression) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    /**
     * 检查表达式（状态）是否为true，为false则抛出异常
     *
     * @param expression 表达式
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * 检查表达式（状态）是否为true，为false则抛出异常，并带上message
     *
     * @param expression 表达式
     * @param message 状态检查失败时抛出异常的消息
     */
    public static void checkState(boolean expression, String message) {
        Objects.requireNonNull(message);
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 检查表达式（状态）是否为true，为false则抛出异常，并带上message构造器
     *
     * @param expression 表达式
     * @param messageSupplier 状态检查失败时抛出异常的消息构造器
     */
    public static void checkState(boolean expression, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        if (!expression) {
            throw new IllegalStateException(messageSupplier.get());
        }
    }

    /**
     * 检查集合是否为empty，为empty则抛出异常
     *
     * @param coll 要检查的集合
     * @param <T> 集合的类型
     * @return 检查成功则返回原集合
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> coll) {
        Objects.requireNonNull(coll);
        if (coll.size() <= 0) {
            throw new IllegalArgumentException();
        }
        return coll;
    }

    /**
     * 检查集合是否为empty，为empty则抛出异常，并带上message
     *
     * @param coll 要检查的集合
     * @param message 检查失败时抛出异常的消息
     * @param <T> 集合的类型
     * @return 检查成功则返回原集合
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> coll, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(coll, message);
        if (coll.size() <= 0) {
            throw new IllegalArgumentException(message);
        }
        return coll;
    }

    /**
     * 检查集合是否为empty，为empty则抛出异常，并带上message构造器
     *
     * @param coll 要检查的集合
     * @param messageSupplier 检查失败时抛出异常的消息构造器
     * @param <T> 集合的类型
     * @return 检查成功则返回原集合
     */
    public static <T> Collection<T> checkNotEmpty(Collection<T> coll, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(coll, messageSupplier);
        if (coll.size() <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
        return coll;
    }

    /**
     * 检查数组是否为empty，为empty则抛出异常
     *
     * @param arr 要检查的数组
     * @param <T> 数组的类型
     * @return 检查成功则返回原数组
     */
    public static <T> T[] checkNotEmpty(T[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length <= 0) {
            throw new IllegalArgumentException();
        }
        return arr;
    }

    /**
     * 检查数组是否为empty，为empty则抛出异常，并带上message
     *
     * @param arr 要检查的数组
     * @param message 检查失败时抛出异常的消息
     * @param <T> 数组的类型
     * @return 检查成功则返回原数组
     */
    public static <T> T[] checkNotEmpty(T[] arr, String message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(arr, message);
        if (arr.length <= 0) {
            throw new IllegalArgumentException(message);
        }
        return arr;
    }

    /**
     * 检查数组是否为empty，为empty则抛出异常，并带上message构造器
     *
     * @param arr 要检查的数组
     * @param messageSupplier 检查失败时抛出异常的消息构造器
     * @param <T> 数组的类型
     * @return 检查成功则返回原数组
     */
    public static <T> T[] checkNotEmpty(T[] arr, Supplier<String> messageSupplier) {
        Objects.requireNonNull(messageSupplier);
        Objects.requireNonNull(arr, messageSupplier);
        if (arr.length <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
        return arr;
    }
}
