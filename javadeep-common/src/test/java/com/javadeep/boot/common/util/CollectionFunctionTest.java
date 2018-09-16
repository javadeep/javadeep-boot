package com.javadeep.boot.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionFunctionTest {

    @Test
    public void test_IS_NOT_EMPTY() {

        Assert.assertFalse(CollectionFunction.IS_NOT_EMPTY.apply(null));
        Assert.assertFalse(CollectionFunction.IS_NOT_EMPTY.apply(Collections.emptyList()));
        Assert.assertTrue(CollectionFunction.IS_NOT_EMPTY.apply(new ArrayList<Integer>(){{add(1);}}));
    }

    @Test
    public void test_IS_EMPTY() {
        Assert.assertTrue(CollectionFunction.IS_EMPTY.apply(null));
        Assert.assertTrue(CollectionFunction.IS_EMPTY.apply(Collections.emptyList()));
        Assert.assertFalse(CollectionFunction.IS_EMPTY.apply(new ArrayList<Integer>(){{add(1);}}));
    }
}
