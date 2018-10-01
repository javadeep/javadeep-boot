package com.javadeep.boot.common.util;

import org.junit.Assert;
import org.junit.Test;

public class StringFunctionTest {

    @Test
    public void test_EQUALS_IGNORE_CASE() {
        Assert.assertTrue(StringFunction.EQUALS_IGNORE_CASE.apply("abc", "AbC"));
    }

    @Test
    public void test_IS_EMPTY() {
        Assert.assertTrue(StringFunction.IS_EMPTY.apply(null));
    }

    @Test
    public void test_IS_NOT_EMPTY() {
        Assert.assertTrue(StringFunction.IS_NOT_EMPTY.apply("a"));
    }
}
