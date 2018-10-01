package com.javadeep.boot.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识需要校验方法种的参数
 *
 * @author javadeep
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JavadeepValid {

    /**
     * 校验器，默认不提供校验器
     */
    Class<?> value() default Void.class;

    /**
     * 方法名
     */
    String method() default "";

    /**
     * 是否启用failFast失败策略
     */
    boolean failFast() default true;

    /**
     * 是否执行hibernateValidate
     */
    boolean enableHibernateValidate() default true;

    /**
     * HibernateValidator校验错误码
     */
    int hibernateErrorCode() default 0;

    /**
     * HibernateValidator校验的分组
     */
    Class<?>[] hibernateGroups() default { };
}
