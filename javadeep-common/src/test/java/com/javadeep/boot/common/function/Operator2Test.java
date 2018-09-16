package com.javadeep.boot.common.function;

import org.junit.Assert;
import org.junit.Test;

public class Operator2Test {

    @Test
    public void testLeft() {
        Assert.assertEquals(2, Operator2.left().apply(2, 3));
    }

    @Test
    public void testRight() {
        Assert.assertEquals(3, Operator2.right().apply(2, 3));
    }
}
