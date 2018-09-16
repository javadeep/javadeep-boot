package com.javadeep.boot.common.base;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PreconditionsTest {

    @Test
    public void testCheckArgument_withoutMessage_true() {
        Preconditions.checkArgument(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_withoutMessage_false() {
        Preconditions.checkArgument(false);
    }

    @Test
    public void testCheckArgument_withMessage_true() {
        Preconditions.checkArgument(true, "true");
    }

    @Test
    public void testCheckArgument_withMessage_false() {
        try {
            Preconditions.checkArgument(false, "false");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckArgument_withMessageSupplier_true() {
        Preconditions.checkArgument(true, () -> "true");
    }

    @Test
    public void testCheckArgument_withMessageSupplier_false() {
        try {
            Preconditions.checkArgument(false, () -> "false");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckState_withoutMessage_true() {
        Preconditions.checkState(true);
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckState_withoutMessage_false() {
        Preconditions.checkState(false);
    }

    @Test
    public void testCheckState_withMessage_true() {
        Preconditions.checkState(true, "true");
    }

    @Test
    public void testCheckState_withMessage_false() {
        try {
            Preconditions.checkState(false, "false");
        } catch (IllegalStateException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckState_withMessageSupplier_true() {
        Preconditions.checkState(true, () -> "true");
    }

    @Test
    public void testCheckState_withMessageSupplier_false() {
        try {
            Preconditions.checkState(false, () -> "false");
        } catch (IllegalStateException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckNotEmptyColl_withoutMessage_true() {
        Assert.assertEquals(new ArrayList<Integer>(){{add(1);}},
                Preconditions.checkNotEmpty(new ArrayList<Integer>(){{add(1);}}));
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmptyColl_withoutMessage_false() {
        Preconditions.checkNotEmpty((Collection<Integer>) null);
    }

    @Test
    public void testCheckNotEmptyColl_withMessage_true() {
        Assert.assertEquals(new ArrayList<Integer>(){{add(1);}},
                Preconditions.checkNotEmpty(new ArrayList<Integer>(){{add(1);}}, "true"));
    }

    @Test
    public void testCheckNotEmptyColl_withMessage_false() {
        try {
            Preconditions.checkNotEmpty(Collections.emptyList(), "false");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckNotEmptyColl_withMessageSupplier_true() {
        Assert.assertEquals(new ArrayList<Integer>(){{add(1);}},
                Preconditions.checkNotEmpty(new ArrayList<Integer>(){{add(1);}}, () -> "true"));
    }

    @Test
    public void testCheckNotEmptyColl_withMessageSupplier_false() {
        try {
            Preconditions.checkNotEmpty(((Collection<Integer>) null), () -> "false");
        } catch (NullPointerException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckNotEmptyArr_withoutMessage_true() {
        Assert.assertArrayEquals(new Integer[]{1},
                Preconditions.checkNotEmpty(new Integer[]{1}));
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotEmptyArr_withoutMessage_false() {
        Preconditions.checkNotEmpty((Integer[]) null);
    }

    @Test
    public void testCheckNotEmptyArr_withMessage_true() {
        Assert.assertArrayEquals(new Integer[]{1},
                Preconditions.checkNotEmpty(new Integer[] {1}, "true"));
    }

    @Test
    public void testCheckNotEmptyArr_withMessage_false() {
        try {
            Preconditions.checkNotEmpty(new Integer[]{}, "false");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }

    @Test
    public void testCheckNotEmptyArr_withMessageSupplier_true() {
        Assert.assertArrayEquals(new Integer[]{1},
                Preconditions.checkNotEmpty(new Integer[]{1}, () -> "true"));
    }

    @Test
    public void testCheckNotEmptyArr_withMessageSupplier_false() {
        try {
            Preconditions.checkNotEmpty((Integer[]) null, () -> "false");
        } catch (NullPointerException e) {
            Assert.assertEquals("false", e.getMessage());
        }
    }
}
