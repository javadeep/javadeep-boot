package com.javadeep.boot.common.data;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 两种可能类型，一般左值表示异常，右值为正常值
 *
 * @author javadeep
 * @since 1.0.0
 *
 * @param <L> 左值类型，一般表示异常
 * @param <R> 右值类型，一般表示正常值
 */
public interface Either<L, R> {

    /**
     * 构建右值的Either对象
     *
     * @param right 右值
     * @param <L> 左值类型
     * @param <R> 右值类型
     * @return 返回构建好的Either对象
     */
    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    /**
     * 构建左值的Either对象
     *
     * @param left 左值
     * @param <L> 左值类型
     * @param <R> 右值类型
     * @return 返回构建好的Either对象
     */
    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    /**
     * 获取左值，如为右值则抛出异常
     *
     * @return 返回左值，如为右值则抛出异常
     */
    L getLeft();

    /**
     * 获取右值，如为左值则抛出异常
     *
     * @return 返回右值，如为左值则抛出异常
     */
    R getRight();

    /**
     * 判断是否为左值
     *
     * @return 返回是否为左值
     */
    boolean isLeft();

    /**
     * 判断是否为右值
     *
     * @return 返回是否为右值
     */
    boolean isRight();

    /**
     * 右值则返回原值，否则返回other
     *
     * @param other 非右值时的返回值
     * @return 右值则返回原值，否则返回other
     */
    default R orElse(R other) {
        return isRight() ? getRight() : other;
    }

    /**
     * 右值则返回原值，否则Supplier提供默认值
     *
     * @param other 非右值时执行的函数
     * @return 右值则返回原值，否则Supplier提供默认值
     */
    default R orElseGet(Supplier<? extends R> other) {
        Objects.requireNonNull(other);
        return isRight() ? getRight() : other.get();
    }

    /**
     * 右值时返回原值，否则通过other映射
     *
     * @param other 映射函数
     * @return 右值时返回原值，否则通过other映射
     */
    default R orElseMap(Function<? super L, ? extends R> other) {
        Objects.requireNonNull(other, "other is null");
        return isRight() ? getRight() : other.apply(getLeft());
    }

    /**
     * 右值时返回原值，否则抛出exceptionFunction映射后的异常
     *
     * @param exceptionFunction 异常映射函数
     * @param <X> 要抛出异常的类型
     * @return 右值时返回原值，否则抛出exceptionFunction映射后的异常
     * @throws X 不为右值时抛出的异常
     */
    default <X extends Throwable> R orElseThrow(Function<? super L, X> exceptionFunction) throws X {
        Objects.requireNonNull(exceptionFunction, "exceptionFucntion is null");
        if (isRight()) {
            return getRight();
        }
        throw exceptionFunction.apply(getLeft());
    }

    /**
     * 对左值的消费
     *
     * @param action 左值消费函数
     * @return 返回消费后的原值
     */
    default Either<L, R> onLeft(Consumer<? super L> action) {
        Objects.requireNonNull(action, "action is null");
        if (isLeft()) {
            action.accept(getLeft());
        }
        return this;
    }

    /**
     * 对右值的消费
     *
     * @param action 右值消费函数
     * @return 返回消费后的原值
     */
    default Either<L, R> onRight(Consumer<? super R> action) {
        Objects.requireNonNull(action, "action is null");
        if (isRight()) {
            action.accept(getRight());
        }
        return this;
    }

    /**
     * 映射到另一个Either
     *
     * @param leftMapper 左值映射函数
     * @param rightMapper 右值映射函数
     * @param <X> 左值映射后的类型
     * @param <Y> 右值映射后的类型
     * @return 返回映射后的Either
     */
    default <X, Y> Either<X, Y> map(Function<? super L, ? extends X> leftMapper,
                                    Function<? super R, ? extends Y> rightMapper) {
        Objects.requireNonNull(leftMapper, "leftMapper is null");
        Objects.requireNonNull(rightMapper, "rightMapper is null");
        return isRight() ? right(rightMapper.apply(getRight())) : left(leftMapper.apply(getLeft()));
    }

    /**
     * 映射到另一个Either（仅映射右值）
     *
     * @param mapper 右值映射函数
     * @param <Y> 右值映射后的类型
     * @return 返回映射后的Either
     */
    default <Y> Either<L, Y> mapRight(Function<? super R, ? extends Y> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return isRight() ? right(mapper.apply(getRight())) : left(getLeft());
    }

    /**
     * 映射到另一个Either（仅映射左值）
     *
     * @param mapper 左值映射函数
     * @param <X> 左值映射后的类型
     * @return 返回映射后的Either
     */
    default <X> Either<X, R> mapLeft(Function<? super L, ? extends X> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return isLeft() ? left(mapper.apply(getLeft())) : right(getRight());
    }

    /**
     * Either的映射
     *
     * @param leftMapper 左值映射函数
     * @param rightMapper 右值映射函数
     * @param <U> 映射后值得类型
     * @return 返回映射后的值
     */
    default <U> U fold(Function<? super L, ? extends U> leftMapper, Function<? super R, ? extends U> rightMapper) {
        Objects.requireNonNull(leftMapper, "leftMapper is null");
        Objects.requireNonNull(rightMapper, "rightMappper is null");
        return isRight() ? rightMapper.apply(getRight()) : leftMapper.apply(getLeft());
    }

    /**
     * 对Either兑现的消费
     *
     * @param action 消费函数
     * @return 返回原Either对象
     */
    default Either<L, R> peek(Consumer<? super Either<L, R>> action) {
        Objects.requireNonNull(action, "action is null");
        action.accept(this);
        return this;
    }

    final class Right<L, R> implements Either<L, R> {

        private final R value;

        private Right(R value) {
            this.value = value;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException("getLeft() on Right");
        }

        @Override
        public R getRight() {
            return value;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || (obj instanceof Right && Objects.equals(value, ((Right<?, ?>) obj).value));
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return String.format("Right(%s)", value.toString());
        }
    }

    final class Left<L, R> implements Either<L, R> {

        private final L value;

        private Left(L value) {
            this.value = value;
        }

        @Override
        public L getLeft() {
            return value;
        }

        @Override
        public R getRight() {
            throw new NoSuchElementException("getRight() on Left");
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || (obj instanceof Left && Objects.equals(value, ((Left<?, ?>) obj).value));
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return String.format("Left(%s)", value.toString());
        }
    }
}
