package com.javadeep.boot.common.function;

        import java.util.function.Function;

/**
 * Function1，Function的扩展
 *
 * @author javadeep
 * @since 1.0.0
 */
@FunctionalInterface
public interface Function1<T, R> extends Function<T, R> {

    /**
     * Function --> Predicate
     *
     * @param f   Function函数
     * @param <T> 要转换的类型
     * @return 返回Predicate
     */
    static <T> Predicate1<T> toPredicate(Function<? super T, ? extends Boolean> f) {
        return f::apply;
    }

    /**
     * 两个函数的复合函数（f(g(x))
     *
     * @param f   函数f
     * @param g   函数g
     * @param <T> 函数g入参类型
     * @param <U> 函数g出参类型，函数f入参类型
     * @param <V> 函数f出参类型
     * @return 返回组合后的函数
     */
    static <T, U, V> Function1<T, V> compose(Function<? super U, ? extends V> f, Function<? super T, ? extends U> g) {
        return t -> f.apply(g.apply(t));
    }

    /**
     * 两个函数的andThen
     *
     * @param f   函数f
     * @param g   函数g
     * @param <T> 函数f入参类型
     * @param <U> 函数f出参类型，函数g入参类型
     * @param <V> 函数g出参类型
     * @return 返回andThen后的函数
     */
    static <T, U, V> Function1<T, V> andThen(Function<? super T, ? extends U> f, Function<? super U, ? extends V> g) {
        return t -> g.apply(f.apply(t));
    }

    /**
     * 高阶函数compose
     *
     * @param <T> 第一个函数的入参
     * @param <U> 第一个函数的出参，第二个函数的入参
     * @param <V> 第二个函数的出参
     * @return 返回compose后的高阶函数
     */
    static <T, U, V> Function1<? super Function<? super U, ? extends V>,
            ? extends Function1<? super Function<? super T, ? extends U>,
                    ? extends Function1<? super T, ? extends V>>> higherCompose() {
        return x -> y -> t -> x.apply(y.apply(t));
    }

    /**
     * 高阶函数andThen
     *
     * @param <T> 第一个函数的入参
     * @param <U> 第一个函数的出参，第二个函数的入参
     * @param <V> 第二个函数的出参
     * @return 返回andThen后的高阶函数
     */
    static <T, U, V> Function1<? super Function<? super T, ? extends U>,
            ? extends Function1<? super Function<? super U, ? extends V>,
                    ? extends Function<? super T, ? extends V>>> higherAndThen() {
        return x -> y -> t -> y.apply(x.apply(t));
    }
}
