package com.javadeep.boot.common.util;

import com.javadeep.boot.common.function.Function1;
import com.javadeep.boot.common.function.Function2;

/**
 * String工具类函数
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class StringFunction {

    private StringFunction() {
        throw new UnsupportedOperationException();
    }

    /**
     * StringUtil.equalsIgnoreCase(String, String)
     */
    public static final Function2<String, String, Boolean> EQUALS_IGNORE_CASE = StringUtil::equalsIgnoreCase;

    /**
     * StringUtil.isEmpty(CharSequence)
     */
    public static final Function1<CharSequence, Boolean> IS_EMPTY = StringUtil::isEmpty;

    /**
     * StringUtil.isNotEmpty(CharSequence)
     */
    public static final Function1<CharSequence, Boolean> IS_NOT_EMPTY = StringUtil::isNotEmpty;
}
