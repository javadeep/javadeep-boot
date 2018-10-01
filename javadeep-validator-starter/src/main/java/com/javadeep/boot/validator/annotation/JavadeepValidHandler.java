package com.javadeep.boot.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识为校验处理器
 *
 * @author javadeep
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JavadeepValidHandler {

    /**
     * 处理器名称，为空默认为方法名
     *
     */
    String value() default "";
}
