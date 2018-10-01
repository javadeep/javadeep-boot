package com.javadeep.boot.common.util;

/**
 * 数组相关工具类
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class ArrayUtil {

    private ArrayUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断数组是否为空
     *
     * @param array 要判断的数组
     * @return 返回数组是否为空
     */
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length <= 0;
    }

    /**
     * 判断数组是否不为空
     *
     * @param array 要判断的数组
     * @return 返回数组是否不为空
     */
    public static boolean isNotEmpty(final Object[] array) {
        return !isEmpty(array);
    }
}
