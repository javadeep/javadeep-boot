package com.javadeep.boot.common.data;

import com.javadeep.boot.common.function.CheckedRunnable;
import com.javadeep.boot.common.function.CheckedSupplier;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 对于有可能出现异常的对象的封装
 *
 * @author javadeep
 * @since 1.0.0
 *
 * @param <T> 封装的对象
 *
 */
public interface Try<T> {

    /**
     * 根据CheckedSupplier创建Try
     *
     * @param supplier CheckedSupplier
     * @param <T> 对象的类型
     * @return 返回创造的Try对象
     */
    static <T> Try<T> of(CheckedSupplier<? extends T> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    /**
     * 根据CheckedSupplier返回值，如有异常则返回defaultValue
     *
     * @param supplier CheckedSupplier
     * @param defaultValue 抛出异常后的默认值
     * @param <T> 对象的类型
     * @return 返回对象的值，如有异常，则返回defaultValue
     */
    static <T> T ofOrDefault(CheckedSupplier<? extends T> supplier, T defaultValue) {

        try {
            return supplier.get();
        } catch (Throwable t) {
            return defaultValue;
        }
    }

    /**
     * 根据CheckedRunnable创建Try
     *
     * @param runnable CheckedRunnable
     * @return 返回创建好的Try对象
     */
    static Try<Void> run(CheckedRunnable runnable) {
        try {
            runnable.run();
            return new Success<>(null);
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    /**
     * 创建一个不返回异常的Try
     *
     * @param value 创建Try的对象
     * @param <T> 对象的类型
     * @return 返回创建好的Try对象
     */
    static <T> Try<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * 创建一个返回异常的Try
     *
     * @param throwable 异常
     * @param <T> Try对象的类型
     * @return 返回一个返回异常的Try
     */
    static <T> Try<T> failure(Throwable throwable) {
        return new Failure<>(throwable);
    }

    /**
     * 返回Try的值，Success时返回原值，否则抛出异常
     *
     * @return 返回Try的值，Failure时抛出异常
     */
    T get();

    /**
     * 返回异常，Success时抛出异常
     *
     * @return 返回异常
     */
    Throwable getCause();

    /**
     * 判断Try对象是否Success
     *
     * @return 返回是否Success
     */
    boolean isSuccess();

    /**
     * 判断Try对象是否Failure
     *
     * @return 返回是否Failure
     */
    boolean isFailure();

    /**
     * Success时对值的处理
     *
     * @param action 对值处理得函数
     * @return 返回自身Try对象
     */
    default Try<T> onSuccess(Consumer<? super T> action) {
        Objects.requireNonNull(action, "action is null");
        if (isSuccess()) {
            action.accept(get());
        }
        return this;
    }

    /**
     * Failure时对异常的处理
     *
     * @param action 对异常处理的函数
     * @return 返回自身Try对象
     */
    default Try<T> onFailure(Consumer<? super Throwable> action) {
        Objects.requireNonNull(action, "action is null");
        if (isFailure()) {
            action.accept(getCause());
        }
        return this;
    }

    /**
     * Success时返回原值，否则返回other
     *
     * @param other Failure时返回的值
     * @return Success时返回原值，否则返回other
     */
    default T orElse(T other) {
        return isSuccess() ? get() : other;
    }

    /**
     * Success时返回原值，否则由Supplier提供默认值
     *
     * @param other Failure时执行的函数
     * @return Success时返回原值，否则由Supplier提供默认值
     */
    default T orElseGet(Supplier<? extends T> other) {
        Objects.requireNonNull(other, "other is null");
        return isSuccess() ? get() : other.get();
    }

    /**
     * Success时返回原值，否则通过other函数映射值
     *
     * @param other Failure时的映射函数
     * @return Success时返回原值，否则通过other函数映射值
     */
    default T orElseMap(Function<? super Throwable, ? extends T> other) {
        Objects.requireNonNull(other, "other is null");
        return isFailure() ? other.apply(getCause()) : get();
    }

    /**
     * Success时返回原值，否则抛出exceptionFunction映射后的异常
     *
     * @param exceptionFunction 异常映射函数
     * @param <X> 异常的类型
     * @return Success时返回原值，否则抛出exceptionFunction映射后的异常
     * @throws X Failure时抛出的异常
     */
    default <X extends Throwable> T orElseThrow(Function<? super Throwable, X> exceptionFunction) throws X {
        Objects.requireNonNull(exceptionFunction, "exceptionFunction is null");
        if (isFailure()) {
            throw  exceptionFunction.apply(getCause());
        }
        return get();
    }

    /**
     * 转换成另一个类型的Try
     *
     * @param mapper 转换函数
     * @param <U> 转换后的类型
     * @return 返回U类型的Try
     */
    default <U> Try<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (isFailure()) {
            return failure(getCause());
        }
        return Try.of(() -> mapper.apply(get()));
    }

    /**
     * Failure时根据值恢复函数恢复
     *
     * @param f 值恢复函数
     * @return 返回恢复后的Try对象
     */
    default Try<T> recover(Function<? super Throwable, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        if (isFailure()) {
            return Try.of(() -> f.apply(getCause()));
        }
        return this;
    }

    /**
     * Failure时根据Try恢复函数恢复
     *
     * @param f Try恢复函数
     * @return 返回恢复后的Try对象
     */
    default Try<T> recoverWith(Function<? super Throwable, ? extends Try<T>> f) {
        Objects.requireNonNull(f, "f is null");
        if (isSuccess()) {
            return this;
        }
        try {
            return f.apply(getCause());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    /**
     * Try的映射
     *
     * @param successMapper Success时的映射函数
     * @param failureMapper Failure时的映射函数
     * @param <U> 映射后的类型
     * @return 返回映射后的值
     */
    default <U> U fold(Function<? super T, ? extends U> successMapper,
                       Function<? super Throwable, ? extends U> failureMapper) {
        Objects.requireNonNull(successMapper, "successMapper is null");
        Objects.requireNonNull(failureMapper, "failureMapper is null");
        return isSuccess() ? successMapper.apply(get()) : failureMapper.apply(getCause());
    }

    /**
     * 对Try对象的消费
     *
     * @param action 消费函数
     * @return 返回原Try对象
     */
    default Try<T> peek(Consumer<? super Try<T>> action) {
        Objects.requireNonNull(action, "action is null");
        action.accept(this);
        return this;
    }

    /**
     * 转换成Either对象
     *
     * @return 返回转换后的Either对象
     */
    default Either<Throwable, T> toEither() {
        return isSuccess() ? Either.right(get()) : Either.left(getCause());
    }

    /**
     * 转换成Optioanl对象
     *
     * @return 返回转换后的Optional对象
     */
    default Optional<T> toOptional() {
        return isSuccess() ? Optional.ofNullable(get()) : Optional.empty();
    }

    final class Success<T> implements Try<T> {

        private final T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Throwable getCause() {
            throw new UnsupportedOperationException("getCause on Success");
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || (obj instanceof Success && Objects.equals(value, ((Success<?>) obj).value));
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value.toString());
        }

    }

    final class Failure<T> implements Try<T> {

        private final RuntimeException cause;

        private Failure(Throwable throwable) {
            this.cause = new RuntimeException(throwable);
        }

        @Override
        public T get() {
            throw cause;
        }

        @Override
        public Throwable getCause() {
            return cause.getCause();
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || (obj instanceof Failure && Objects.equals(cause, ((Failure<?>) obj).cause));
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(cause);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", cause.toString());
        }
    }
}
