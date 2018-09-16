package com.javadeep.boot.common.util;


import com.javadeep.boot.common.function.Function1;

import java.util.Collection;

/**
 * Collection工具类函数
 *
 * @author javadeep
 * @since 1.0.0
 */
public final class CollectionFunction {

    private CollectionFunction() {
        throw new UnsupportedOperationException();
    }

    /**
     * CollectionUtil.isEmpty(Collection)
     */
    public static final Function1<Collection, Boolean> IS_EMPTY = CollectionUtil::isEmpty;

    /**
     * CollectionUtil.isNotEmpty(Collection)
     */
    public static final Function1<Collection, Boolean> IS_NOT_EMPTY = CollectionUtil::isNotEmpty;


}
