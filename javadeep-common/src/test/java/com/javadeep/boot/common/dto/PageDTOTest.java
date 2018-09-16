package com.javadeep.boot.common.dto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PageDTOTest {

    @Test
    public void testBuilderSuccess() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(2);
        datas.add(3);
        PageDTO<Integer> dto = PageDTO.builder(datas)
                .pageNo(2)
                .pageSize(3)
                .totalRecords(10)
                .totalPages(4)
                .build();
        Assert.assertEquals(3, dto.getDatas().size());
        Assert.assertEquals(2, dto.getPageNo());
        Assert.assertEquals(3, dto.getPageSize());
        Assert.assertEquals(10, dto.getTotalRecords());
        Assert.assertEquals(4, dto.getTotalPages());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderFail() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(2);
        datas.add(3);
        PageDTO.builder(datas).build();
    }

    @Test
    public void testOfConvert() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(2);
        datas.add(3);
        PageDTO<Integer> dto = PageDTO.builder(datas)
                .pageNo(2)
                .pageSize(3)
                .totalRecords(10)
                .totalPages(4)
                .build();
        PageDTO<String> newDTO = PageDTO.of(dto, String::valueOf);
        Assert.assertEquals(3, newDTO.getDatas().size());
        Assert.assertEquals(2, newDTO.getPageNo());
        Assert.assertEquals(3, newDTO.getPageSize());
        Assert.assertEquals(10, newDTO.getTotalRecords());
        Assert.assertEquals(4, newDTO.getTotalPages());
    }
}
