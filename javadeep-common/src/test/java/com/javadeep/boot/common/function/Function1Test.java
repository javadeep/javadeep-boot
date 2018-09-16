package com.javadeep.boot.common.function;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class Function1Test {

    private static final Function<Integer, Double> TRIPLE = a -> a * 3.0;

    private static final Function1<Double, String> STR = a -> String.valueOf((int)(a + 0.5));

    @Test
    public void testToPredicate() {
        Function<Integer, Boolean> f = a -> a > 0;
        Assert.assertTrue(Function1.toPredicate(f).test(10));
        Assert.assertFalse(Function1.toPredicate(f).test(0));
    }

    @Test
    public void testCompose() {
        Assert.assertEquals("15", Function1.compose(STR, TRIPLE).apply(5));
    }

    @Test
    public void testAndThen() {
        Assert.assertEquals("15", Function1.andThen(TRIPLE, STR).apply(5));
    }

    @Test
    public void testHigherCompose() {
        Assert.assertEquals("15",
                Function1.<Integer, Double, String>higherCompose().apply(STR).apply(TRIPLE).apply(5));
    }

    @Test
    public void testHigherAndThen() {
        Assert.assertEquals("15",
                Function1.<Integer, Double, String>higherAndThen().apply(TRIPLE).apply(STR).apply(5));
    }
}
