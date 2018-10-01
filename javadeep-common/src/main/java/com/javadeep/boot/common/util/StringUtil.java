package com.javadeep.boot.common.util;

import java.util.Objects;

/**
 * String工具类
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class StringUtil {

    private StringUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 不区分大小写的equals
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 返回equals的结果
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return Objects.isNull(str1) ? Objects.isNull(str2) : str1.equalsIgnoreCase(str2);
    }

    /**
     * 是否为Empty
     *
     * @param cs 要判断的字符串
     * @return 返回是否为Empty
     */
    public static boolean isEmpty(CharSequence cs) {
        return Objects.isNull(cs) || cs.length() == 0;
    }

    /**
     * 是否不为Empty
     *
     * @param cs 要判断的字符串
     * @return 返回是否不为Empty
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
}
