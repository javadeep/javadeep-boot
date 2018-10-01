package com.javadeep.boot.common.function;

/**
 * 受检的Supplier
 *
 * @author javadeep
 * @since 1.0.0
 *
 * @param <R> 对象类型
 */
@FunctionalInterface
public interface CheckedSupplier<R> {

    /**
     * 获得结果（有可能出现异常）
     *
     * @return 返回结果
     * @throws Throwable 可能抛出的异常
     */
    R get() throws Throwable;
}
