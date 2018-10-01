package com.javadeep.boot.common.util;

import com.javadeep.boot.common.function.Function1;

/**
 * 数组工具类函数
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class ArrayFunction {

    private ArrayFunction() {
        throw new UnsupportedOperationException();
    }

    /**
     * ArrayUtil.isEmpty(Object[])
     */
    public static final Function1<Object[], Boolean> IS_EMPTY = ArrayUtil::isEmpty;

    /**
     * ArrayUtil.isNotEmpty(Object[])
     */
    public static final Function1<Object[], Boolean> IS_NOT_EMPTY = ArrayUtil::isNotEmpty;
}
