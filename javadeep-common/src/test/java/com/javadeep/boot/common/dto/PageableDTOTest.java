package com.javadeep.boot.common.dto;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class PageableDTOTest {

    @Test
    public void testPageable() {
        PageableDTO dto = new PageableDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        dto.setOrders(Collections.emptyList());
        Assert.assertEquals(1, dto.getPageNo());
    }
}
