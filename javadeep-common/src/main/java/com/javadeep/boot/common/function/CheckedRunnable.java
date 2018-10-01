package com.javadeep.boot.common.function;

import java.util.concurrent.Callable;

/**
 * 受检的Runnable
 *
 * @author javadeep
 * @since 1.0.0
 */
@FunctionalInterface
public interface CheckedRunnable {

    /**
     * 执行
     *
     * @throws Throwable 可能抛出的异常
     */
    void run() throws Throwable;
}
