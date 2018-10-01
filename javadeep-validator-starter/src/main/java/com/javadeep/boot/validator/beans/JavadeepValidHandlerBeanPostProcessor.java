package com.javadeep.boot.validator.beans;

import com.javadeep.boot.common.util.ArrayFunction;
import com.javadeep.boot.common.util.StringFunction;
import com.javadeep.boot.validator.annotation.JavadeepValidHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * JavadeepValid参数校验Bean注册器
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class JavadeepValidHandlerBeanPostProcessor implements BeanPostProcessor {

    private final Set<String> baseScanPackages;

    public static JavadeepValidHandlerBeanPostProcessor of(Set<String> baseScanPackages) {
        return new JavadeepValidHandlerBeanPostProcessor(baseScanPackages);
    }

    private JavadeepValidHandlerBeanPostProcessor(Set<String> baseScanPackages) {
        this.baseScanPackages = baseScanPackages;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        baseScanPackages.stream()
                .filter(bean.getClass().getName()::startsWith)
                .forEach(baseScanPackage -> postProcess(bean));

        return bean;
    }

    private void postProcess(Object bean) {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (ArrayFunction.IS_EMPTY.apply(methods)) {
            return;
        }

        Arrays.stream(methods).forEach(method ->
            Optional.ofNullable(AnnotationUtils.findAnnotation(method, JavadeepValidHandler.class))
                    .ifPresent(handler -> JavadeepValidHandlerFactory.addHandler(
                            StringFunction.IS_EMPTY.apply(handler.value()) ? method.getName() : handler.value(),
                    bean, method)));

    }
}
