package com.javadeep.boot.common.util;

import java.util.Collection;

/**
 * Collection工具类
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class CollectionUtil {

    private CollectionUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断Collection是否为空
     *
     * @param coll 要判断的集合
     * @return 返回Collection是否为空
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 判断Collection是否不为空
     *
     * @param coll 要判断的集合
     * @return 返回Collection是否不为空
     */
    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }
}
