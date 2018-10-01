package com.javadeep.boot.validator.beans;

import com.javadeep.boot.common.data.Try;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 参数校验处理器Bean
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class JavadeepValidHandlerBean {

    private final Object bean;
    private final Method method;

    private JavadeepValidHandlerBean(Object bean, Method method) {

        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(method, "method is null");

        this.bean = bean;
        this.method = method;
    }

    public static JavadeepValidHandlerBean of(Object bean, Method method) {
        return new JavadeepValidHandlerBean(bean, method);
    }

    public final void invoke(Object... args) {
        Try.of(() -> method.invoke(bean, args))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof JavadeepValidHandlerBean
                && Objects.equals(bean, ((JavadeepValidHandlerBean)obj).bean)
                && Objects.equals(method, ((JavadeepValidHandlerBean) obj).method));
    }

    @Override
    public int hashCode() {
        return Objects.hash(bean, method);
    }

    @Override
    public String toString() {
        return String.format("JavadeepValidHandlerBean(%s-%s)", bean.toString(), method.toString());
    }
}
