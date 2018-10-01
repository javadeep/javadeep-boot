package com.javadeep.boot.common.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayFunctionTest {

    @Test
    public void test_IS_EMPTY() {
        Assert.assertTrue(ArrayFunction.IS_EMPTY.apply(new Integer[]{}));
    }

    @Test
    public void test_IS_NOT_EMPTY() {
        Assert.assertTrue(ArrayFunction.IS_NOT_EMPTY.apply(new Integer[]{1}));
    }
}
