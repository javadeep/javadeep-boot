package com.javadeep.boot.validator.beans;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 参数校验处理器工厂
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class JavadeepValidHandlerFactory {

    private JavadeepValidHandlerFactory() {
        throw new UnsupportedOperationException();
    }

    private static final Map<String, JavadeepValidHandlerBean> HANDLER_MAP = new HashMap<>();

    /**
     * 注册ValidHandler
     *
     * @param handlerName 处理器名称
     * @param bean 处理器对应的bean
     * @param method 对应校验器的Method
     */
    public synchronized static void addHandler(String handlerName, Object bean, Method method) {
        Objects.requireNonNull(handlerName, "handlerName is null");
        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(method, "method is null");
        String key = formatKey(handlerName, bean.getClass());
        JavadeepValidHandlerBean handlerBean = JavadeepValidHandlerBean.of(bean, method);
        if (HANDLER_MAP.containsKey(key)) {
            if (Objects.equals(HANDLER_MAP.get(key), handlerBean)) {
                throw new ExceptionInInitializerError("duplicate key: " + key);
            }
        } else {
            HANDLER_MAP.put(key, handlerBean);
        }
    }

    /**
     * 根据handlerName和Class获取处理器bean
     *
     * @param handlerName 处理器名称
     * @param clazz class
     * @return 返回处理器bean
     */
    public static Optional<JavadeepValidHandlerBean> getHandler(String handlerName, Class<?> clazz) {
        String key = formatKey(handlerName, clazz);
        return Optional.ofNullable(HANDLER_MAP.get(key));
    }

    private static String formatKey(String handlerName, Class<?> clazz) {
        return String.format("%s-%s", handlerName, clazz.getName());
    }
}
